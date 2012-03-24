package tk.nekotech.sChat;

import org.bukkit.entity.Player;

public class PlayerCheck{

	SChat p;
	Player r = null;

	public PlayerCheck(SChat p){
		this.p = p;
	}

	public Player check(String n){
		Player[] list = p.getServer().getOnlinePlayers();
		String argstr = "";
		int cnt = 0;
		for (int i = 1; i < list.length; ++i)
			if (i == list.length - 1)
				argstr += list[i] + "";
			else
				argstr += list[i] + " ";
		for (Player p : list)
			if (p.getName().contains(n)){
				cnt = cnt + 1;
				r = p;
			}
		if (cnt > 1)
			return null;
		else if (cnt == 0)
			return null;
		return r;
	}
}
