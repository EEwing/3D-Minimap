package com.quentillionaire.dddminimap.Command.Commands;

import com.quentillionaire.dddminimap.Command.BaseCommand;
import com.quentillionaire.dddminimap.Utillity.Logger;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class CommandReloadMap extends BaseCommand {

    @Override
    public String getCommandName() {
        return "setSize";
    }

    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "Resize the minimap";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        Logger.info("Command " + getCommandName() + " just got used");
        //Add code here to reload the minimap
        sender.addChatMessage(new ChatComponentText("Reloading 3D-Minimap.."));
    }
}
