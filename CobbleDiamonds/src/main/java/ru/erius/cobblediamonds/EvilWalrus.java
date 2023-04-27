package ru.erius.cobblediamonds;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.ai.tree.Behavior;
import net.citizensnpcs.api.ai.tree.BehaviorStatus;
import net.citizensnpcs.api.event.NPCDamageByBlockEvent;
import net.citizensnpcs.api.event.NPCDamageByEntityEvent;
import net.citizensnpcs.api.event.NPCDeathEvent;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.TraitInfo;
import net.citizensnpcs.api.trait.trait.*;
import net.citizensnpcs.trait.HologramTrait;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EvilWalrus implements Listener {

    private final static List<NPC> NPCS = new ArrayList<>();
    private final NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "");
    private final BossBar bossBar = Bukkit.createBossBar(ChatColor.RED + "" + ChatColor.BOLD + "Evil Walrus",
            BarColor.RED, BarStyle.SOLID);
    private final static int HEALTH = 100;

    public EvilWalrus() {
        NPCS.add(npc);
        npc.setName("Evil Walrus");
        npc.setProtected(false);
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, new ItemStack(Material.NETHERITE_SWORD));
        npc.getOrAddTrait(SkinTrait.class).setSkinPersistent("evil_walrus",
                "UdL23fFUl28pHbSUy0VVGpNE8uPJEJwT3U4pn90O3xke+je1Ij8mM1rHRRudTMP1eH+JvFVgc110rYFTyWY6a8BHDPOxN6NmTuk4v+ZCfcpUzUU6N3W2fD1vVMlEEghSP/OeN9RixV5GJR3+orAmOgJEGw2kYJsv33BdJxlCEUL982eMHHs0LJXkuXXrzkCVHaGJuQ9Q4uG9rjVXupbheEhVr2c2ueeqmW3/+ho9pfxkCE0hhA7o+X7AIOy3EmopFG8G06aatnxW74Ac8+hFHZILs33pGLRY+3SKQ2bdFvgNP5Uk23B7TpaBEkeA8uAfpMebnKIWJGKtrESBXCf10YmpbI6dtzNqqVAQl/jdOn0Agy8P9Y9OLcXJh7yIyZJY4Lk59SB2uGEuVKc3QLRRFW8w+h4b0Pugl6nfrqX6NlrxcDAgg2KsaVg4d3Z799wfqRRI0ZizrN6xKLBXlVrJTO2fI2yItOodnXWcHI+yDlncMhAQTHC7SdYVRULXmiYQ1A6Un8f/7OqT+dxEi+sIxenV8X9ChEpIKXctjb4zIEMHxrUL0TyMS8jZMWPq9BwyyYNek31HFKNXPMJv5fwlyExidor6gR3zOXWh4fOXj48V92AQwMJEB3hPpsHmaD6UP/GFLYNtQ4KCXTQoCGgiCVwOghkwpVFtYMZHEaLFDSI=",
                "ewogICJ0aW1lc3RhbXAiIDogMTYyODcyNjU5MDM5NywKICAicHJvZmlsZUlkIiA6ICJmMTA0NzMxZjljYTU0NmI0OTkzNjM4NTlkZWY5N2NjNiIsCiAgInByb2ZpbGVOYW1lIiA6ICJ6aWFkODciLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGJiOGUyYzY0YjExMWU2ODk0ODA3ZThhNGU1YTVlNjIxZjJlMDI4ZGJmZGZhNGEwZDliMmQ4YTUxYjk0Mjg4MiIKICAgIH0KICB9Cn0=");
        Bukkit.getOnlinePlayers().forEach(bossBar::addPlayer);
    }

    public void spawn(Location location) {
        Behavior behavior = new EvilWalrusBehaviour();
        npc.getDefaultGoalController().addBehavior(behavior, 1);
        Bukkit.getPluginManager().registerEvents((Listener) behavior, CobbleDiamonds.getInstance());
        npc.spawn(location);
        ((Player) npc.getEntity()).getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(HEALTH);
        ((Player) npc.getEntity()).getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(30);
        ((Player) npc.getEntity()).setHealth(HEALTH);
    }

    private final class EvilWalrusBehaviour implements Behavior, Listener {

        private BehaviorStatus state = BehaviorStatus.RUNNING;

        @Override
        public void reset() {
            state = null;
        }

        @Override
        public BehaviorStatus run() {
            checkIfDead();
            switch (state) {
                case FAILURE:
                    break;
                case RUNNING:
                    break;
                case SUCCESS:
                    break;
                case RESET_AND_REMOVE:
                    break;
            }
            return state;
        }

        @Override
        public boolean shouldExecute() {
            return lockOnTarget();
        }

        private boolean lockOnTarget() {
            Optional<Entity> optional = npc.getEntity().getWorld().getNearbyEntities(npc.getEntity().getLocation(),
                    30, 30, 30, e -> e.getType() == EntityType.PLAYER).stream().findFirst();
            if (optional.isPresent() && optional.get() != npc.getEntity()) {
                npc.getNavigator().setTarget(optional.get(), true);
                return true;
            }
            return false;
        }

        private void onKill() {
            Bukkit.getServer().broadcastMessage("u dead");
            npc.getNavigator().setTarget(null, false);
            state = BehaviorStatus.RUNNING;
        }

        private void checkIfDead() {
            if (npc.getEntity().isDead())
                state = BehaviorStatus.RESET_AND_REMOVE;
        }

        @EventHandler
        private void onNPCDeath(NPCDeathEvent evt) {
            bossBar.removeAll();
            npc.destroy();
            evt.getDrops().add(new ItemStack(Material.NETHERITE_SWORD));
            evt.getNPC().getEntity().getWorld().createExplosion(evt.getNPC().getEntity().getLocation(), 2);
            SpawnWalrusListener.isAlive = false;
        }

        @EventHandler
        private void onNPCDamage(NPCDamageByEntityEvent evt) {
            updateBossBar();
        }

        @EventHandler
        private void onNpcDamage(NPCDamageByBlockEvent evt) {
            updateBossBar();
        }

        private void updateBossBar() {
            bossBar.setProgress(((Player) npc.getEntity()).getHealth() / HEALTH);
        }
    }
}
