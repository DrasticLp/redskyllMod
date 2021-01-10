package com.drastic.redskyll.util.handlers;

import com.drastic.redskyll.client.gui.GuiColor;
import com.drastic.redskyll.client.gui.GuiColorPrimary;
import com.drastic.redskyll.client.gui.GuiEscape;
import com.drastic.redskyll.client.gui.GuiForge;
import com.drastic.redskyll.client.gui.GuiOilingMachine;
import com.drastic.redskyll.client.gui.GuiSinteringFurnace;
import com.drastic.redskyll.client.gui.GuiWrench;
import com.drastic.redskyll.objects.blocks.containers.ContainerForge;
import com.drastic.redskyll.objects.blocks.containers.ContainerOilingMachine;
import com.drastic.redskyll.objects.blocks.containers.ContainerSinteringFurnace;
import com.drastic.redskyll.objects.tileentities.TileEntityForge;
import com.drastic.redskyll.objects.tileentities.TileEntityOilingMachine;
import com.drastic.redskyll.objects.tileentities.TileEntitySinteringFurnace;
import com.drastic.redskyll.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID == Reference.GUI_CAULDRON)
            return new ContainerSinteringFurnace(player.inventory, (TileEntitySinteringFurnace)world.getTileEntity(new BlockPos(x, y, z)));
        if(ID == Reference.GUI_OIL_MACHINE)
            return new ContainerOilingMachine(player.inventory, (TileEntityOilingMachine)world.getTileEntity(new BlockPos(x, y, z)));
        if(ID == Reference.GUI_FORGE)
            return new ContainerForge(player.inventory, (TileEntityForge)world.getTileEntity(new BlockPos(x, y, z)));
        return null;
    }
    
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID == 0)
            return new GuiEscape();
        if(ID == 1)
            return new GuiColor();
        if(ID == 2)
            return new GuiColorPrimary();
        if(ID == Reference.GUI_CAULDRON)
            return new GuiSinteringFurnace(player.inventory, (TileEntitySinteringFurnace)world.getTileEntity(new BlockPos(x, y, z)));
        if(ID == 15)
            return new GuiWrench();
        if(ID == Reference.GUI_OIL_MACHINE)
            return new GuiOilingMachine(player.inventory, (TileEntityOilingMachine)world.getTileEntity(new BlockPos(x, y, z)));
        if(ID == Reference.GUI_FORGE)
            return new GuiForge(player.inventory, (TileEntityForge)world.getTileEntity(new BlockPos(x, y, z)));
        return null;
    }
}
