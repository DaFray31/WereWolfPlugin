package io.github.ph1lou.werewolfplugin.commands.admin.ingame;

import io.github.ph1lou.werewolfapi.Commands;
import io.github.ph1lou.werewolfapi.enumlg.StateLG;
import io.github.ph1lou.werewolfplugin.Main;
import io.github.ph1lou.werewolfplugin.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CommandWhitelist implements Commands {

    private final Main main;

    public CommandWhitelist(Main main) {
        this.main = main;
    }


    @Override
    public void execute(CommandSender sender, String[] args) {


        GameManager game = main.getCurrentGame();


        if (!sender.hasPermission("a.use") && !sender.hasPermission("a.whitelist.use") && !game.getHosts().contains(((Player) sender).getUniqueId())) {
            sender.sendMessage(game.translate("werewolf.check.permission_denied"));
            return;
        }

        if (args.length != 1) {
            return;
        }

        if (Bukkit.getPlayer(args[0]) == null) {
            sender.sendMessage(game.translate("werewolf.check.offline_player"));
            return;
        }

        Player player = Bukkit.getPlayer(args[0]);
        UUID uuid = player.getUniqueId();

        if (game.getWhiteListedPlayers().contains(uuid)) {
            sender.sendMessage(game.translate("werewolf.commands.admin.whitelist.remove"));
            game.removePlayerOnWhiteList(uuid);
        } else {
            sender.sendMessage(game.translate("werewolf.commands.admin.whitelist.add"));
            game.addPlayerOnWhiteList(uuid);
            if (game.isState(StateLG.LOBBY)) {
                game.join(player);
            }
        }
    }
}
