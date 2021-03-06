package xyz.garslity093.gerrysworld.ecoadditions.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import xyz.garslity093.gerrysworld.ecoadditions.utils.CoinUtils;
import xyz.garslity093.gerrysworld.ecoadditions.utils.ConfigUtils;

import java.util.ArrayList;
import java.util.List;

public final class EcoAdditionsCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (args[0].equalsIgnoreCase("giveCoinTo")) {
            if (args.length >= 3) {
                Player player = Bukkit.getPlayer(args[1]);
                if (player != null) {
                    double amount = Double.parseDouble(args[2]);
                    if (amount != 0) {
                        player.getInventory().addItem(CoinUtils.getCoinItemStack(amount));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e成功。"));
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c数量不能为 0 。"));
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c该玩家不在线。"));
                }
            }
        } else if (args[0].equalsIgnoreCase("reload")) {
            ConfigUtils.reloadConfig();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e重载成功。"));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        List<String> list = new ArrayList<>();
        if (args.length == 1) {
            list.add("giveCoinTo");
            list.add("reload");
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("giveCoinTo")) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    list.add(player.getName());
                }
            }
        }
        return list;
    }
}
