package tk.nekotech.sChat;

import java.lang.reflect.Method;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import tk.nekotech.sSuiteLib.ErrorHandling;
import tk.nekotech.sSuiteLib.Logging;

public class ChatInformer{

	@SuppressWarnings("unused")
	private ErrorHandling err;
	private Logging log;
	@SuppressWarnings("unused")
	private Method m;
	public Object oo;
	private String[] mlist;
	private Class<?>[] clist;
	private int num = 0;

	public ChatInformer(SChat p){
		this.err = new ErrorHandling(p, SChat.cfg, "debugMode", "silentMode");
		this.log = new Logging(p, SChat.cfg, "debugMode", "silentMode");
	}

	public int getListener(JavaPlugin jp, Class<?> c, String method){
		String pname = jp.getDescription().getName();
		try{
			m = c.getDeclaredMethod(method);
		}catch (SecurityException e){
			err.PST(e);
		}catch (NoSuchMethodException e){
			err.PST(e);
		}
		for (Class<?> i : clist){
			if (i.equals(c)){
				log.severe("Chat listener already created for plugin \"" + pname + "\"!");
				return 0;
			}
		}
		clist[num] = c;
		mlist[num] = method;
		num = num + 1;
		return 1;
	}

	public void informChat(Player p, String m, String t){
		for (Class<?> c : clist){

		}
	}
}