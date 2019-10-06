package net.savagellc.savageskyblock.goal

import net.prosavage.baseplugin.serializer.commonobjects.SerializableItem
import net.savagellc.savageskyblock.core.IPlayer
import org.bukkit.Bukkit

data class Quest(
    val name: String,
    val guiDisplayItem: SerializableItem,
    val guiDisplayIndex: Int,
    val type: QuestGoal,
    val goalParameter: String,
    val amountTillComplete: Int,
    val commandsToExecuteOnCompletion: List<String>
) {


    fun isComplete(amount: Int): Boolean {
        return amountTillComplete <= amount
    }


    fun giveRewards(iPlayer: IPlayer) {
        val player = iPlayer.getPlayer()
        for (command in commandsToExecuteOnCompletion) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("{player}", player.name))
        }
    }

}