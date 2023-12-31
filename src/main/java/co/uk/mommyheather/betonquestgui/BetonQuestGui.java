package co.uk.mommyheather.betonquestgui;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;

import org.betonquest.betonquest.BetonQuest;

public final class BetonQuestGui extends JavaPlugin
{
    public static BetonQuestGui INSTANCE;
    private BetonQuest betonQuest;
    public HashMap<String, Boolean> players = new HashMap<String, Boolean>();

    @Override
    public void onEnable()
    {
        INSTANCE = this;
        if (this.getServer().getPluginManager().isPluginEnabled("BetonQuest"))
        {
            this.getCommand("bqgui").setExecutor(new BQGuiCommand());
            this.getCommand("bqgui").setTabCompleter(new BQGuiCommandCompleter());
            this.betonQuest = (BetonQuest) this.getServer().getPluginManager().getPlugin("BetonQuest");
            this.betonQuest.registerConversationIO("gui", GuiConversationIO.class);
            Bukkit.getMessenger().registerOutgoingPluginChannel(this, "betonquestgui:main");
            Bukkit.getMessenger().registerIncomingPluginChannel(this, "betonquestgui:main", new PacketHandler());
            Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        }
    }

    public BetonQuest getBetonQuest()
    {
        return this.betonQuest;
    }
}
