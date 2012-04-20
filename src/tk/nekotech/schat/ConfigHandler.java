package tk.nekotech.sChat;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.bukkit.configuration.Configuration;

import tk.nekotech.sSuiteLib.ErrorHandling;
import tk.nekotech.sSuiteLib.Logging;

public class ConfigHandler{

	private final SChat sc;
	private static Configuration cfg;
	private final File cfgf = new File("plugins/sChat/config.yml");
	private PrintWriter pw;
	private boolean firstRun;
	private boolean sendStats;
	private boolean silentMode;
	//private boolean autoGen;
	private boolean autoUpdate;
	private boolean autoDownload;
	private boolean debugMode;
	//private boolean disableOnFinish;
	private String cfgV;
	private boolean devBuilds;
	private final Logging log;
	private final ErrorHandling err;

	public ConfigHandler(SChat sc){
		this.sc = sc;
		cfg = sc.getConfig();
		log = new Logging(sc, cfg, "debugMode", "silentMode");
		err = new ErrorHandling(sc, cfg, "debugMode", "silentMode");
	}

	public final void addComments(){
		try{
			pw = new PrintWriter(new FileWriter(cfgf));
		}catch (IOException e){
			err.PST(e);
			log.warning("Error adding comments to config.yml!");
		}
		firstRun = cfg.getBoolean("firstRun");
		sendStats = cfg.getBoolean("sendStats");
		silentMode = cfg.getBoolean("silentMode");
		//autoGen = cfg.getBoolean("autoGen");
		autoUpdate = cfg.getBoolean("autoUpdate");
		autoDownload = cfg.getBoolean("autoDownload");
		debugMode = cfg.getBoolean("debugMode");
		//disableOnFinish = cfg.getBoolean("disableOnFinish");
		devBuilds = cfg.getBoolean("devBuilds");
		cfgV = cfg.getString("cfgV");
		pw.println("#SChat config file");
		pw.println("");
		pw.println("#Config version. Do not change. DO NOT CHANGE! Changing");
		pw.println("#may cause undesirable results!");
		pw.println("cfgV: \"" + cfgV + "\"");
		pw.println("");
		pw.println("#Is it the first run?");
		pw.println("#DO NOT CHANGE! It may still be true after you run it,");
		pw.println("#but just leave it. It is supposed to do that. LEAVE IT!");
		pw.println("#Leave it alone. Don't change it. Let it be.");
		pw.println("#Used to manage displaying certain messages at startup.");
		pw.println("firstRun: " + firstRun);
		pw.println("");
		pw.println("#Should the plugin send usage stats to metrics.griefcraft.com?");
		pw.println("sendStats: " + sendStats);
		pw.println("");
		pw.println("#Display output on startup/shutdown? Does not display error messages or");
		pw.println("#debug messages even if debug is enabled. Does not display error messages.");
		pw.println("#Will still display 'SChat vX.X.X enabled!'");
		pw.println("silentMode: " + silentMode);
		pw.println("");
		//pw.println("#Weather or not to automatically generate the permissions files on startup");
		//pw.println("autoGen: " + autoGen);
		//pw.println("");
		pw.println("#Weather or not to check for updates");
		pw.println("autoUpdate: " + autoUpdate);
		pw.println("");
		pw.println("#Should it automatically download the newest SChat.jar?");
		pw.println("autoDownload: " + autoDownload);
		pw.println("");
		//pw.println("#Disable the plugin once it finishes?");
		//pw.println("#Please note that setting this to true will disable");
		//pw.println("#the commands to regenerate the permissions files!");
		//pw.println("#Should not be true if autoGen is false.");
		//pw.println("disableOnFinish: " + disableOnFinish);
		//pw.println("");
		pw.println("#Download the latest dev build? May have bugs and errors.");
		pw.println("#If set to false, will only download the latest recommended builds");
		pw.println("devBuilds: " + devBuilds);
		pw.println("");
		pw.println("#Debug if needed for errors/bugs/info.");
		pw.println("debugMode: " + debugMode);
		pw.close();
	}

	public final void restore(){
		cfg = sc.getConfig();
		try{
			pw = new PrintWriter(new FileWriter(cfgf));
		}catch (IOException e){
			err.PST(e);
			log.warning("Error adding comments to config.yml!");
		}
		firstRun = cfg.getBoolean("firstRun");
		sendStats = cfg.getBoolean("sendStats");
		silentMode = cfg.getBoolean("silentMode");
		//autoGen = cfg.getBoolean("autoGen");
		autoUpdate = cfg.getBoolean("autoUpdate");
		autoDownload = cfg.getBoolean("autoDownload");
		debugMode = cfg.getBoolean("debugMode");
		//disableOnFinish = cfg.getBoolean("disableOnFinish");
		devBuilds = cfg.getBoolean("devBuilds");
		cfgV = sc.pversion;
		pw.println("cfgV: \"" + cfgV + "\"");
		pw.println("firstRun: " + firstRun);
		pw.println("sendStats: " + sendStats);
		pw.println("silentMode: " + silentMode);
		//pw.println("autoGen: " + autoGen);
		pw.println("autoUpdate: " + autoUpdate);
		pw.println("autoDownload: " + autoDownload);
		//pw.println("disableOnFinish: " + disableOnFinish);
		pw.println("devBuilds: " + devBuilds);
		pw.println("debugMode: " + debugMode);
		pw.close();
	}
}
