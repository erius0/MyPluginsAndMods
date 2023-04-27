package ru.erius.lifeastnt.TNTHandler;

import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;
import ru.erius.lifeastnt.LifeAsTNT;

import java.util.HashMap;

public class TransformTNT {

    public final static HashMap<Player, TaskEntry> playerTask = new HashMap<>();

    private static void transformPlayer(Player player) {
        FallingBlock tnt = player.getWorld().spawnFallingBlock(player.getLocation(), Material.TNT.createBlockData());
        tnt.setGravity(false);
        tnt.setDropItem(false);
        tnt.setInvulnerable(true);
        tnt.getPersistentDataContainer().set(new NamespacedKey(LifeAsTNT.plugin, "time"), PersistentDataType.INTEGER, -1);
        player.setInvisible(true);
        Vector from = player.getLocation().toVector();
        int taskId = Bukkit.getScheduler().runTaskTimer(LifeAsTNT.plugin, () -> {
            Vector to = player.getLocation().toVector();
            tnt.setVelocity(from.subtract(to).multiply(-1));
            from.copy(to);
        }, 0, 1).getTaskId();
        playerTask.put(player, new TaskEntry(tnt, taskId));
    }

    private static void undoTransformation(Player player) {
        player.setInvisible(false);
        Bukkit.getScheduler().cancelTask(playerTask.get(player).getTaskId());
        playerTask.get(player).getTnt().remove();
        playerTask.remove(player);
    }

    public static void onCommand(CommandSender sender, String[] args) {
        if (args.length == 0)
            if (sender instanceof Player)
                checkMap(sender, (Player) sender);
            else
                sender.sendMessage(ChatColor.RED + "You are not a player");
        else {
            Player player = Bukkit.getServer().getPlayer(args[0]);
            if (player != null)
                checkMap(sender, player);
            else
                sender.sendMessage(ChatColor.RED + "Such player is offline or doesn't exist");
        }
    }

    private static void checkMap(CommandSender sender, Player player) {
        if (playerTask.containsKey(player)) {
            undoTransformation(player);
            sender.sendMessage(ChatColor.GREEN + "Undoing the transformation");
        } else {
            transformPlayer(player);
            sender.sendMessage(ChatColor.GREEN + "Transforming");
        }
    }

    private static class TaskEntry {
        private final FallingBlock tnt;
        private final int taskId;

        public TaskEntry(FallingBlock tnt, int taskId) {
            this.tnt = tnt;
            this.taskId = taskId;
        }

        public FallingBlock getTnt() {
            return tnt;
        }

        public int getTaskId() {
            return taskId;
        }
    }
}
