package ru.erius.walrusgaming;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.List;

public class MessageHandler {

    private final static FileConfiguration CONFIG = ConfigHandler.getConfig();
    private final static BaseComponent[] MESSAGE = createMessage();
    private final static ItemStack BOOK = createBook();

    public static void heal(CommandSender sender) {
        if (sender instanceof Player)
            ((Player) sender).setHealth(20);
        else
            sender.sendMessage(ChatColor.RED + "You are not a player");
    }

    public static void feed(CommandSender sender) {
        if (sender instanceof Player)
            ((Player) sender).setFoodLevel(20);
        else
            sender.sendMessage(ChatColor.RED + "You are not a player");
    }

    public static void printMessage(CommandSender sender, String[] args) {
        if (args.length == 0)
            sender.spigot().sendMessage(MESSAGE);
        else {
            Player player = Bukkit.getServer().getPlayer(args[0]);
            if (player == null)
                sender.sendMessage(ChatColor.RED + "Such player is offline or doesn't exist");
            else
                player.spigot().sendMessage(MESSAGE);
        }
    }

    public static void giveBook(CommandSender sender, String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player) {
                ((Player) sender).getInventory().addItem(BOOK);
                sender.sendMessage(ChatColor.GREEN + "book");
            } else
                sender.sendMessage(ChatColor.RED + "You are not a player");
        } else {
            Player player = Bukkit.getServer().getPlayer(args[0]);
            if (player != null) {
                sender.sendMessage(ChatColor.GREEN + "book");
                player.getInventory().addItem(BOOK);
            } else
                sender.sendMessage(ChatColor.RED + "Such player is offline or doesn't exist");
        }
    }

    private static ItemStack createBook() {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta) book.getItemMeta();
        if (meta == null)
            return new ItemStack(Material.DIRT);
        List<BaseComponent[]> pages = new ArrayList<>();
        ConfigurationSection bookSection = CONFIG.getConfigurationSection("book");
        if (bookSection == null)
            return book;
        String title = bookSection.getString("title");
        meta.setTitle(title == null ? "Missing title" : ChatColor.translateAlternateColorCodes('&', title));
        String lore = bookSection.getString("lore");
        if (lore != null)
            meta.setLore(List.of(ChatColor.translateAlternateColorCodes('&', lore)));
        else
            meta.setLore(List.of("Missing lore"));
        String author = bookSection.getString("author");
        meta.setAuthor(author == null ? "Missing author" : ChatColor.translateAlternateColorCodes('&', author));
        ConfigurationSection pagesSection = bookSection.getConfigurationSection("pages");
        if (pagesSection == null)
            return new ItemStack(Material.DIRT);
        int i = 1;
        ConfigurationSection pageSection = pagesSection.getConfigurationSection("page" + i);
        while (pageSection != null) {
            i++;
            int j = 1;
            ConfigurationSection part = pageSection.getConfigurationSection("t" + j);
            ComponentBuilder builder = new ComponentBuilder();
            while (part != null) {
                j++;
                appendJSON(part, builder, j);
                part = pageSection.getConfigurationSection("t" + j);
            }
            pages.add(builder.create());
            pageSection = pagesSection.getConfigurationSection("page" + i);
        }
        meta.spigot().setPages(pages);
        book.setItemMeta(meta);
        return book;
    }

    private static BaseComponent[] createMessage() {
        ComponentBuilder builder = new ComponentBuilder();
        ConfigurationSection msg = CONFIG.getConfigurationSection("message");
        if (msg == null)
            return new ComponentBuilder().append("Missing text. Check walrus.yml").color(ChatColor.RED).create();
        int i = 1;
        ConfigurationSection part = msg.getConfigurationSection("t" + i);
        while (part != null) {
            i++;
            appendJSON(part, builder, i);
            part = msg.getConfigurationSection("t" + i);
        }
        return builder.create();
    }

    private static void appendJSON(ConfigurationSection part, ComponentBuilder builder, int i) {
        if (part == null)
            return;
        String text = part.getString("text");
        text = text == null ? "" : text;
        String colorTxt = part.getString("color");
        colorTxt = colorTxt != null ? colorTxt : "";
        net.md_5.bungee.api.ChatColor color;
        try {
            color = ChatColor.of(colorTxt);
        } catch (IllegalArgumentException e) {
            WalrusGaming.plugin.getLogger().severe("Wrong color name in section t" + i);
            color = ChatColor.WHITE;
        }
        boolean bold = part.getBoolean("bold");
        boolean italic = part.getBoolean("italic");
        boolean underlined = part.getBoolean("underlined");
        boolean strikethrough = part.getBoolean("strikethrough");
        boolean obfuscated = part.getBoolean("obfuscated");
        builder.append(text).color(color).bold(bold).italic(italic).underlined(underlined)
                .strikethrough(strikethrough).obfuscated(obfuscated);
        ConfigurationSection click = part.getConfigurationSection("click_event");
        if (click != null) {
            String action = click.getString("action");
            String value = click.getString("value");
            try {
                ClickEvent clickEvent = new ClickEvent(ClickEvent.Action.valueOf(action), value);
                builder.event(clickEvent);
            } catch (IllegalArgumentException e) {
                WalrusGaming.plugin.getLogger().severe("Wrong click event action in section t" + i);
            }
        }
    }
}
