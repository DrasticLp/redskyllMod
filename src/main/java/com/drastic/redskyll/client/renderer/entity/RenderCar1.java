package com.drastic.redskyll.client.renderer.entity;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.drastic.redskyll.client.models.entity.Car;
import com.drastic.redskyll.client.models.entity.ModelFuse;
import com.drastic.redskyll.init.BlockInit;
import com.drastic.redskyll.objects.entity.EntityCar;
import com.drastic.redskyll.objects.entity.base.EntityFuseBase;
import com.drastic.redskyll.util.Reference;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderFallingBlock;
import net.minecraft.client.renderer.entity.RenderHorse;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCar1 extends Render<EntityCar>
{
    public RenderCar1(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
        this.shadowSize = 0.5F;
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityCar entity, double x, double y, double z, float entityYaw, float partialTicks)
    {

        IBlockState iblockstate = BlockInit.CAR_1.getDefaultState();

        if(iblockstate.getRenderType() == EnumBlockRenderType.MODEL)
        {
            World world = entity.getEntityWorld();

            if(iblockstate != world.getBlockState(new BlockPos(entity)) && iblockstate.getRenderType() != EnumBlockRenderType.INVISIBLE)
            {
                this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
                GlStateManager.pushMatrix();
                GlStateManager.disableLighting();
                Tessellator tessellator = Tessellator.getInstance();
                BufferBuilder bufferbuilder = tessellator.getBuffer();

                if(this.renderOutlines)
                {
                    GlStateManager.enableColorMaterial();
                    GlStateManager.enableOutlineMode(this.getTeamColor(entity));
                }

                bufferbuilder.begin(7, DefaultVertexFormats.BLOCK);
                BlockPos blockpos = new BlockPos(entity.posX, entity.getEntityBoundingBox().maxY, entity.posZ);
                GlStateManager.translate((float)(x - (double)blockpos.getX() - 0.5D), (float)(y - (double)blockpos.getY()), (float)(z - (double)blockpos.getZ() - 0.5D));
                BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
                blockrendererdispatcher.getBlockModelRenderer().renderModel(world, blockrendererdispatcher.getModelForState(iblockstate), iblockstate, blockpos, bufferbuilder, false, MathHelper.getPositionRandom(entity.getPosition()));
                tessellator.draw();

                if(this.renderOutlines)
                {
                    GlStateManager.disableOutlineMode();
                    GlStateManager.disableColorMaterial();
                }

                GlStateManager.enableLighting();
                GlStateManager.popMatrix();
                super.doRender(entity, x, y, z, entityYaw, partialTicks);
            }

        }
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityCar entity)
    {
        // TODO Auto-generated method stub
        return TextureMap.LOCATION_BLOCKS_TEXTURE;
    }
}