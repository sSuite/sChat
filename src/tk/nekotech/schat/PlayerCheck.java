package tk.nekotech.sChat;

import org.bukkit.entity.Player;

public class PlayerCheck{

	SChat p;

	public PlayerCheck(SChat p){
		this.p = p;
	}

	public Player[] check(String n){
		Player[] list = p.getServer().getOnlinePlayers();
		String string2 = "";
		for (int i = 1; i < list.length; ++i)
			string2 += list[i] + " ";
		return null;
	}
}
