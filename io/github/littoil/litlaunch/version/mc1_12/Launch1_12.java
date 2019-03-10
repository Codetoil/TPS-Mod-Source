package io.github.littoil.litlaunch.version.mc1_12;

import io.github.littoil.litlaunch.launchcommon.Command;
import io.github.littoil.litlaunch.launchcommon.LaunchCommon;
import io.github.littoil.litlaunch.launchcommon.LaunchTPSMOD;
import io.github.littoil.litlaunch.launchcommon.proxy.CommonClientProxy;
import io.github.littoil.litlaunch.launchcommon.proxy.CommonServerProxy;
import io.github.littoil.litlaunch.launchforge.LaunchForge;
import io.github.littoil.litlaunch.version.mc1_12.proxy.ClientProxy1_12;
import io.github.littoil.litlaunch.version.mc1_12.proxy.ServerProxy1_12;
import io.github.littoil.tpsmod.TPSMod;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = Launch1_12.MODID, version = Launch1_12.VERSION)
public class Launch1_12 extends LaunchForge<FMLPreInitializationEvent, FMLInitializationEvent, FMLPostInitializationEvent, FMLServerStartingEvent> {
	public static final String VERSION = "1.12-0.0.0.2";

	public Launch1_12()
	{
		LaunchTPSMOD.INSTANCE.LOGGER = new Logger1_12();
		if (!setProxy())
		{
			LaunchTPSMOD.INSTANCE.LOGGER.error("Proxies not set!");
		}
	}

	public boolean setProxy()
	{
		boolean result;
		if (ccproxy != null)
		{
			LaunchTPSMOD.INSTANCE.LOGGER.error("Tried re-setting proxy!");
			result = false;
		}
		else
		{
			Side side = FMLCommonHandler.instance().getSide();
			switch (side) {
				case CLIENT:
					ccproxy = new ClientProxy1_12();
					result = true;
					break;
				case SERVER:
					ccproxy = new ServerProxy1_12();
					result = true;
					break;
				default:
					LaunchTPSMOD.INSTANCE.LOGGER.error("FML is not sided(client vs server). This may not go well!");
					result = false;
					break;
			}
		}
		return result;
	}

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
	}

	@EventHandler
	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}

	@EventHandler
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}

	@EventHandler
	@Override
	public void serverLoad(FMLServerStartingEvent event) {
		super.serverLoad(event);
		TPSMod.commandList.forEach((command) -> {
			if (Command.Side.SERVER.equals(command.side) || Command.Side.BOTH.equals(command.side))
				event.registerServerCommand(new CommandNew(command));
		});
	}
}
