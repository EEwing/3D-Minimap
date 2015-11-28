package com.quentillionaire.dddminimap.Command.Commands;

import com.quentillionaire.dddminimap.Command.BaseCommand;
import com.quentillionaire.dddminimap.Utillity.Logger;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class CommandSize extends BaseCommand {

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
        sender.addChatMessage(new ChatComponentText("Test 1 2 3"));
    }
}
