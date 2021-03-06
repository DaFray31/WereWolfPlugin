package io.github.ph1lou.werewolfplugin.commands;

import io.github.ph1lou.werewolfapi.Commands;
import io.github.ph1lou.werewolfplugin.Main;
import io.github.ph1lou.werewolfplugin.commands.admin.CommandChange;
import io.github.ph1lou.werewolfplugin.commands.admin.CommandGeneration;
import io.github.ph1lou.werewolfplugin.commands.admin.CommandSize;
import io.github.ph1lou.werewolfplugin.commands.admin.CommandStop;
import io.github.ph1lou.werewolfplugin.commands.admin.ingame.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.*;

public class Admin implements TabExecutor {

    private final Map<String, Commands> listAdminCommands = new HashMap<>();

    public Admin(Main main) {

        listAdminCommands.put("name", new CommandName(main));
        listAdminCommands.put("start", new CommandStart(main));
        listAdminCommands.put("chat", new CommandChat(main));
        listAdminCommands.put("info", new CommandInfo(main));
        listAdminCommands.put("generation", new CommandGeneration(main));
        listAdminCommands.put("setGroup", new CommandSetGroup(main));
        listAdminCommands.put("group", new CommandGroup(main));
        listAdminCommands.put("config", new CommandConfig(main));
        listAdminCommands.put("kill", new CommandKill(main));
        listAdminCommands.put("disc", new CommandDisconnected(main));
        listAdminCommands.put("inv", new CommandInventory(main));
        listAdminCommands.put("tpGroup", new CommandTPGroup(main));
        listAdminCommands.put("role", new CommandRole(main));
        listAdminCommands.put("revive", new CommandRevive(main));
        listAdminCommands.put("fh", new CommandFinalHeal(main));
        listAdminCommands.put("lootStart", new CommandLootStart(main));
        listAdminCommands.put("lootDeath", new CommandLootDeath(main));
        listAdminCommands.put("stuffRole", new CommandStuffRole(main));
        listAdminCommands.put("h", new CommandAdminHelp(main));
        listAdminCommands.put("stop", new CommandStop(main));
        listAdminCommands.put("whitelist", new CommandWhitelist(main));
        listAdminCommands.put("moderator", new CommandModerator(main));
        listAdminCommands.put("host", new CommandHost(main));
        listAdminCommands.put("gamemode", new CommandGamemode(main));
        listAdminCommands.put("tp", new CommandTP(main));
        listAdminCommands.put("size", new CommandSize(main));
        listAdminCommands.put("change", new CommandChange(main));
        listAdminCommands.putAll(main.getListAdminCommands());
    }
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0){
            this.listAdminCommands.get("h").execute(sender, null);
        }
        else this.listAdminCommands.getOrDefault(args[0], this.listAdminCommands.get("h")).execute(sender, Arrays.copyOfRange(args, 1, args.length));
        return true;
    }


	@Override
	public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {

        List<String> temp = new ArrayList<>(this.listAdminCommands.keySet());
        if (args.length == 0) {
            return temp;
        } else if (args.length == 1) {

            for (int i = 0; i < temp.size(); i++) {
                for (int j = 0; j < temp.get(i).length() && j < args[0].length(); j++) {
                    if (temp.get(i).charAt(j) != args[0].charAt(j)) {
                        temp.remove(i);
                        i--;
                        break;
                    }
                }
            }
            return temp;
        }
		return null;
	}
}
