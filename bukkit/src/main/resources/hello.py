import sys
from time import sleep

from mcje.minecraft import Minecraft
import param_MCJE as param
from param_MCJE import PLAYER_ORIGIN as po
from param_MCJE import block
from param_MCJE import entity
from param_MCJE import particle

# Connect to minecraft and open a session as player with origin location
mc = Minecraft.create(address=param.ADRS_MCR, port=param.PORT_MCR)
result = mc.setPlayer(param.PLAYER_NAME, po.x, po.y, po.z)
if "Error" in result:
    sys.exit(result)
else:
    print(result)

mc.postToChat("Hello, Minecraft Server!!")

# axis_flat.reset_minecraft_world(mc, width=48)
# axis_flat.clear_XYZ_axis(mc, wait=0)
# axis_flat.draw_XYZ_axis(mc, wait=0.3)

# mc.setBlocks(0, 180, 0, 5, 200, 5, param.GOLD_BLOCK)
