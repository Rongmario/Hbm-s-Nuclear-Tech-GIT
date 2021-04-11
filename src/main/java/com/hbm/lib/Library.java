package com.hbm.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.base.Predicates;
import com.google.common.collect.Sets;
import com.hbm.blocks.ModBlocks;
import com.hbm.calc.UnionOfTileEntitiesAndBooleans;
import com.hbm.capability.RadiationCapability.EntityRadiationProvider;
import com.hbm.capability.RadiationCapability.IEntityRadioactive;
import com.hbm.entity.mob.EntityHunterChopper;
import com.hbm.entity.projectile.EntityChopperMine;
import com.hbm.handler.WeightedRandomChestContentFrom1710;
import com.hbm.interfaces.IConductor;
import com.hbm.interfaces.IConsumer;
import com.hbm.interfaces.ISource;
import com.hbm.interfaces.Spaghetti;
import com.hbm.items.ModItems;
import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.tileentity.conductor.TileEntityCable;
import com.hbm.tileentity.conductor.TileEntityCableSwitch;
import com.hbm.tileentity.machine.TileEntityDummy;
import com.hbm.tileentity.machine.TileEntityMachineBattery;
import com.hbm.tileentity.machine.TileEntityMachineTransformer;
import com.hbm.tileentity.machine.TileEntityPylonRedWire;
import com.hbm.tileentity.machine.TileEntityWireCoated;

import api.hbm.energy.IBatteryItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.oredict.OreDictionary;

@Spaghetti("this whole class")
public class Library {

	static Random rand = new Random();
	
	//this is a list of UUIDs used for various things, primarily for accessories.
	//for a comprehensive list, check RenderAccessoryUtility.java
	public static String HbMinecraft = "192af5d7-ed0f-48d8-bd89-9d41af8524f8";
	public static String TacoRedneck = "5aee1e3d-3767-4987-a222-e7ce1fbdf88e";
	// Earl0fPudding
	public static String LPkukin = "937c9804-e11f-4ad2-a5b1-42e62ac73077";
	public static String Dafnik = "3af1c262-61c0-4b12-a4cb-424cc3a9c8c0";
	// anna20
	public static String a20 = "4729b498-a81c-42fd-8acd-20d6d9f759e0";
	public static String rodolphito = "c3f5e449-6d8c-4fe3-acc9-47ef50e7e7ae";
	public static String LordVertice = "a41df45e-13d8-4677-9398-090d3882b74f";
	// twillycorn
	public static String CodeRed_ = "912ec334-e920-4dd7-8338-4d9b2d42e0a1";
	public static String dxmaster769 = "62c168b2-d11d-4dbf-9168-c6cea3dcb20e";
	public static String Dr_Nostalgia = "e82684a7-30f1-44d2-ab37-41b342be1bbd";
	public static String Samino2 = "87c3960a-4332-46a0-a929-ef2a488d1cda";
	public static String Hoboy03new = "d7f29d9c-5103-4f6f-88e1-2632ff95973f";
	public static String Dragon59MC = "dc23a304-0f84-4e2d-b47d-84c8d3bfbcdb";
	public static String SteelCourage = "ac49720b-4a9a-4459-a26f-bee92160287a";
	public static String Ducxkskiziko = "122fe98f-be19-49ca-a96b-d4dee4f0b22e";
	
	public static String SweatySwiggs = "5544aa30-b305-4362-b2c1-67349bb499d5";
	public static String Drillgon = "41ebd03f-7a12-42f3-b037-0caa4d6f235b";
	public static String Doctor17 = "e4ab1199-1c22-4f82-a516-c3238bc2d0d1";
	public static String Doctor17PH = "4d0477d7-58da-41a9-a945-e93df8601c5a";
	public static String ShimmeringBlaze = "061bc566-ec74-4307-9614-ac3a70d2ef38";
	public static String FifeMiner = "37e5eb63-b9a2-4735-9007-1c77d703daa3";
	public static String lag_add = "259785a0-20e9-4c63-9286-ac2f93ff528f";
	
	public static Set<String> contributors = Sets.newHashSet(new String[] {
			"06ab7c03-55ce-43f8-9d3c-2850e3c652de", //mustang_rudolf
			"5bf069bc-5b46-4179-aafe-35c0a07dee8b", //JMF781
			});

	//the old list that allowed superuser mode for the ZOMG
	//currently unused
	public static List<String> superuser = new ArrayList<String>();

	// Drillgon200: Not like super users are used for anything, but they could
	// in the future I guess.
	public static void initSuperusers() {
		superuser.add(HbMinecraft);
		superuser.add(TacoRedneck);
		superuser.add(LPkukin);
		superuser.add(Dafnik);
		superuser.add(a20);
		superuser.add(rodolphito);
		// Drillgon200: Pretty sure he did install NEI.
		superuser.add(Ducxkskiziko);
		superuser.add(Drillgon);
	}

	public static boolean checkForHeld(EntityPlayer player, Item item) {
		return player.getHeldItemMainhand().getItem() == item || player.getHeldItemOffhand().getItem() == item;
	}

	public static boolean isObstructed(World world, double x, double y, double z, double a, double b, double c) {
		RayTraceResult pos = world.rayTraceBlocks(new Vec3d(x, y, z), new Vec3d(a, b, c), false, true, true);
		return pos != null && pos.typeOfHit != Type.MISS;
	}

	public static String getShortNumber(long l) {
		if(l >= Math.pow(10, 18)) {
			double res = l / Math.pow(10, 18);
			res = Math.round(res * 100.0) / 100.0;
			return res + "E";
		}
		if(l >= Math.pow(10, 15)) {
			double res = l / Math.pow(10, 15);
			res = Math.round(res * 100.0) / 100.0;
			return res + "P";
		}
		if(l >= Math.pow(10, 12)) {
			double res = l / Math.pow(10, 12);
			res = Math.round(res * 100.0) / 100.0;
			return res + "T";
		}
		if(l >= Math.pow(10, 9)) {
			double res = l / Math.pow(10, 9);
			res = Math.round(res * 100.0) / 100.0;
			return res + "G";
		}
		if(l >= Math.pow(10, 6)) {
			double res = l / Math.pow(10, 6);
			res = Math.round(res * 100.0) / 100.0;
			return res + "M";
		}
		if(l >= Math.pow(10, 3)) {
			double res = l / Math.pow(10, 3);
			res = Math.round(res * 100.0) / 100.0;
			return res + "k";
		}

		return Long.toString(l);
	}

	// Drillgon200: Just realized I copied the wrong method. God dang it.
	// It works though. Not sure why, but it works.
	public static long chargeTEFromItems(IItemHandlerModifiable inventory, int index, long power, long maxPower) {
		if(inventory.getStackInSlot(index).getItem() == ModItems.battery_creative)
		{
			return maxPower;
		}
		
		if(inventory.getStackInSlot(index).getItem() == ModItems.fusion_core_infinite)
		{
			return maxPower;
		}
		
		if(inventory.getStackInSlot(index).getItem() instanceof IBatteryItem) {
			
			IBatteryItem battery = (IBatteryItem) inventory.getStackInSlot(index).getItem();

			long batCharge = battery.getCharge(inventory.getStackInSlot(index));
			long batRate = battery.getDischargeRate();
			
			//in hHe
			long toDischarge = Math.min(Math.min((maxPower - power), batRate), batCharge);
			
			battery.dischargeBattery(inventory.getStackInSlot(index), toDischarge);
			power += toDischarge;
		}
		
		return power;
	}

	//not great either but certainly better
	public static long chargeItemsFromTE(IItemHandlerModifiable inventory, int index, long power, long maxPower) {
		if(inventory.getStackInSlot(index).getItem() instanceof IBatteryItem) {
			IBatteryItem battery = (IBatteryItem) inventory.getStackInSlot(index).getItem();
			ItemStack stack = inventory.getStackInSlot(index);
			
			long batMax = battery.getMaxCharge();
			long batCharge = battery.getCharge(stack);
			long batRate = battery.getChargeRate();
			
			//in hHE
			long toCharge = Math.min(Math.min(power, batRate), batMax - batCharge);
			
			power -= toCharge;
			
			battery.chargeBattery(stack, toCharge);

			if(inventory.getStackInSlot(index) != null && inventory.getStackInSlot(index).getItem() == ModItems.dynosphere_desh && battery.getCharge(stack) >= battery.getMaxCharge())
				inventory.setStackInSlot(index, new ItemStack(ModItems.dynosphere_desh_charged));
			if(inventory.getStackInSlot(index) != null && inventory.getStackInSlot(index).getItem() == ModItems.dynosphere_schrabidium && battery.getCharge(stack) >= battery.getMaxCharge())
				inventory.setStackInSlot(index, new ItemStack(ModItems.dynosphere_schrabidium_charged));
			if(inventory.getStackInSlot(index) != null && inventory.getStackInSlot(index).getItem() == ModItems.dynosphere_euphemium && battery.getCharge(stack) >= battery.getMaxCharge())
				inventory.setStackInSlot(index, new ItemStack(ModItems.dynosphere_euphemium_charged));
			if(inventory.getStackInSlot(index) != null && inventory.getStackInSlot(index).getItem() == ModItems.dynosphere_dineutronium && battery.getCharge(stack) >= battery.getMaxCharge())
				inventory.setStackInSlot(index, new ItemStack(ModItems.dynosphere_dineutronium_charged));
		}
		
		return power;
	}

	public static boolean isArrayEmpty(Object[] array) {
		if(array == null)
			return true;
		if(array.length == 0)
			return true;

		boolean flag = true;

		for(int i = 0; i < array.length; i++) {
			if(array[i] != null)
				flag = false;
		}

		return flag;
	}

	// Drillgon200: useless method but whatever
	public static ItemStack carefulCopy(ItemStack stack) {
		if(stack == null)
			return null;
		else
			return stack.copy();
	}
	
	public static EntityPlayer getClosestPlayerForSound(World world, double x, double y, double z, double radius) {
		double d4 = -1.0D;
		EntityPlayer entity = null;

		for (int i = 0; i < world.loadedEntityList.size(); ++i) {
				Entity entityplayer1 = (Entity)world.loadedEntityList.get(i);

				if (entityplayer1.isEntityAlive() && entityplayer1 instanceof EntityPlayer) {
					double d5 = entityplayer1.getDistanceSq(x, y, z);
					double d6 = radius;

					if ((radius < 0.0D || d5 < d6 * d6) && (d4 == -1.0D || d5 < d4)) {
						d4 = d5;
						entity = (EntityPlayer)entityplayer1;
					}
			}
		}

		return entity;
	}

	public static EntityHunterChopper getClosestChopperForSound(World world, double x, double y, double z, double radius) {
		double d4 = -1.0D;
		EntityHunterChopper entity = null;

		for (int i = 0; i < world.loadedEntityList.size(); ++i) {
				Entity entityplayer1 = (Entity)world.loadedEntityList.get(i);

				if (entityplayer1.isEntityAlive() && entityplayer1 instanceof EntityHunterChopper) {
					double d5 = entityplayer1.getDistanceSq(x, y, z);
					double d6 = radius;

					if ((radius < 0.0D || d5 < d6 * d6) && (d4 == -1.0D || d5 < d4)) {
						d4 = d5;
						entity = (EntityHunterChopper)entityplayer1;
					}
			}
		}

		return entity;
	}

	public static EntityChopperMine getClosestMineForSound(World world, double x, double y, double z, double radius) {
		double d4 = -1.0D;
		EntityChopperMine entity = null;

		for (int i = 0; i < world.loadedEntityList.size(); ++i) {
				Entity entityplayer1 = (Entity)world.loadedEntityList.get(i);

				if (entityplayer1.isEntityAlive() && entityplayer1 instanceof EntityChopperMine) {
					double d5 = entityplayer1.getDistanceSq(x, y, z);
					double d6 = radius;

					if ((radius < 0.0D || d5 < d6 * d6) && (d4 == -1.0D || d5 < d4)) {
						d4 = d5;
						entity = (EntityChopperMine)entityplayer1;
					}
			}
		}

		return entity;
	}

	public static RayTraceResult rayTrace(EntityPlayer player, double length, float interpolation) {
		Vec3d vec3 = getPosition(interpolation, player);
		vec3 = vec3.addVector(0D, (double) player.eyeHeight, 0D);
		Vec3d vec31 = player.getLook(interpolation);
		Vec3d vec32 = vec3.addVector(vec31.x * length, vec31.y * length, vec31.z * length);
		return player.world.rayTraceBlocks(vec3, vec32, false, false, true);
	}
	
	public static RayTraceResult rayTrace(EntityPlayer player, double length, float interpolation, boolean b1, boolean b2, boolean b3) {
		Vec3d vec3 = getPosition(interpolation, player);
		vec3 = vec3.addVector(0D, (double) player.eyeHeight, 0D);
		Vec3d vec31 = player.getLook(interpolation);
		Vec3d vec32 = vec3.addVector(vec31.x * length, vec31.y * length, vec31.z * length);
		return player.world.rayTraceBlocks(vec3, vec32, b1, b2, b3);
	}
	
	
	public static RayTraceResult rayTraceIncludeEntities(EntityPlayer player, double d, float f) {
		Vec3d vec3 = getPosition(f, player);
		vec3 = vec3.addVector(0D, (double) player.eyeHeight, 0D);
		Vec3d vec31 = player.getLook(f);
		Vec3d vec32 = vec3.addVector(vec31.x * d, vec31.y * d, vec31.z * d);
		
		RayTraceResult result = player.world.rayTraceBlocks(vec3, vec32, false, true, true);
		if(result != null)
			vec32 = result.hitVec;
		
		AxisAlignedBB box = new AxisAlignedBB(vec3.x, vec3.y, vec3.z, vec32.x, vec32.y, vec32.z).grow(1D);
		List<Entity> ents = player.world.getEntitiesInAABBexcluding(player, box, Predicates.and(EntitySelectors.IS_ALIVE, entity -> entity instanceof EntityLiving));
		for(Entity ent : ents){
			RayTraceResult test = ent.getEntityBoundingBox().grow(0.3D).calculateIntercept(vec3, vec32);
			if(test != null){
				if(result == null || vec3.squareDistanceTo(result.hitVec) > vec3.squareDistanceTo(test.hitVec)){
					test.typeOfHit = RayTraceResult.Type.ENTITY;
					test.entityHit = ent;
					result = test;
				}
			}
		}
		
		return result;
	}
	
	public static RayTraceResult rayTraceIncludeEntities(World w, Vec3d vec3, Vec3d vec32, @Nullable Entity excluded) {
		RayTraceResult result = w.rayTraceBlocks(vec3, vec32, false, true, true);
		if(result != null)
			vec32 = result.hitVec;
		
		AxisAlignedBB box = new AxisAlignedBB(vec3.x, vec3.y, vec3.z, vec32.x, vec32.y, vec32.z).grow(1D);
		List<Entity> ents = w.getEntitiesInAABBexcluding(excluded, box, Predicates.and(EntitySelectors.IS_ALIVE, entity -> entity instanceof EntityLivingBase));
		for(Entity ent : ents){
			RayTraceResult test = ent.getEntityBoundingBox().grow(0.3D).calculateIntercept(vec3, vec32);
			if(test != null){
				if(result == null || vec3.squareDistanceTo(result.hitVec) > vec3.squareDistanceTo(test.hitVec)){
					test.typeOfHit = RayTraceResult.Type.ENTITY;
					test.entityHit = ent;
					result = test;
				}
			}
		}
		
		return result;
	}
	
	public static Pair<RayTraceResult, List<Entity>> rayTraceEntitiesOnLine(EntityPlayer player, double d, float f){
		Vec3d vec3 = getPosition(f, player);
		vec3 = vec3.addVector(0D, (double) player.eyeHeight, 0D);
		Vec3d vec31 = player.getLook(f);
		Vec3d vec32 = vec3.addVector(vec31.x * d, vec31.y * d, vec31.z * d);
		RayTraceResult result = player.world.rayTraceBlocks(vec3, vec32, false, true, true);
		if(result != null)
			vec32 = result.hitVec;
		AxisAlignedBB box = new AxisAlignedBB(vec3.x, vec3.y, vec3.z, vec32.x, vec32.y, vec32.z).grow(1D);
		List<Entity> ents = player.world.getEntitiesInAABBexcluding(player, box, Predicates.and(EntitySelectors.IS_ALIVE, entity -> entity instanceof EntityLiving));
		Iterator<Entity> itr = ents.iterator();
		while(itr.hasNext()){
			Entity ent = itr.next();
			AxisAlignedBB entityBox = ent.getEntityBoundingBox().grow(0.1);
			RayTraceResult entTrace = entityBox.calculateIntercept(vec3, vec32);
			if(entTrace == null || entTrace.typeOfHit == Type.MISS){
				itr.remove();
			}
		}
		return Pair.of(rayTraceIncludeEntities(player, d, f), ents);
	}
	
	public static RayTraceResult rayTraceEntitiesInCone(EntityPlayer player, double d, float f, float degrees) {
		double cosDegrees = Math.cos(Math.toRadians(degrees));
		Vec3d vec3 = getPosition(f, player);
		vec3 = vec3.addVector(0D, (double) player.eyeHeight, 0D);
		Vec3d vec31 = player.getLook(f);
		Vec3d vec32 = vec3.addVector(vec31.x * d, vec31.y * d, vec31.z * d);
		
		RayTraceResult result = player.world.rayTraceBlocks(vec3, vec32, false, true, true);
		double runningDot = Double.MIN_VALUE;
		
		AxisAlignedBB box = new AxisAlignedBB(vec3.x, vec3.y, vec3.z, vec3.x, vec3.y, vec3.z).grow(1D+d);
		List<Entity> ents = player.world.getEntitiesInAABBexcluding(player, box, Predicates.and(EntitySelectors.IS_ALIVE, entity -> entity instanceof EntityLiving));
		for(Entity ent : ents){
			Vec3d entPos = closestPointOnBB(ent.getEntityBoundingBox(), vec3, vec32);
			Vec3d relativeEntPos = entPos.subtract(vec3).normalize();
			double dot = relativeEntPos.dotProduct(vec31);
			
			if(dot > cosDegrees && dot > runningDot && !isObstructed(player.world, vec3.x, vec3.y, vec3.z, ent.posX, ent.posY + ent.getEyeHeight()*0.75, ent.posZ)){
				runningDot = dot;
				result = new RayTraceResult(ent);
				result.hitVec = new Vec3d(ent.posX, ent.posY + ent.getEyeHeight()/2, ent.posZ);
			}
			
		}
		
		return result;
	}
	
	//Drillgon200: Turns out the closest point on a bounding box to a line is a pretty good method for determine if a cone and an AABB intersect.
	//Actually that was a pretty garbage method. Changing it out for a slightly less efficient sphere culling algorithm that only gives false positives.
	//https://bartwronski.com/2017/04/13/cull-that-cone/
	//Idea is that we find the closest point on the cone to the center of the sphere and check if it's inside the sphere.
	public static boolean isBoxCollidingCone(AxisAlignedBB box, Vec3d coneStart, Vec3d coneEnd, float degrees){
		Vec3d center = box.getCenter();
		double radius = center.distanceTo(new Vec3d(box.maxX, box.maxY, box.maxZ));
		Vec3d V = center.subtract(coneStart);
		double VlenSq = V.lengthSquared();
		Vec3d direction = coneEnd.subtract(coneStart);
		double size = direction.lengthVector();
		double V1len  = V.dotProduct(direction.normalize());
		double angRad = Math.toRadians(degrees);
		double distanceClosestPoint = Math.cos(angRad) * Math.sqrt(VlenSq - V1len*V1len) - V1len * Math.sin(angRad);
		 
		boolean angleCull = distanceClosestPoint > radius;
		boolean frontCull = V1len >  radius + size;
		boolean backCull  = V1len < -radius;
		return !(angleCull || frontCull || backCull);
	}
	
	//Drillgon200: Basically the AxisAlignedBB calculateIntercept method except it clamps to edge instead of returning null
	public static Vec3d closestPointOnBB(AxisAlignedBB box, Vec3d vecA, Vec3d vecB){
		
		Vec3d vec3d = collideWithXPlane(box, box.minX, vecA, vecB);
        Vec3d vec3d1 = collideWithXPlane(box, box.maxX, vecA, vecB);

        if (vec3d1 != null && isClosest(vecA, vecB, vec3d, vec3d1))
        {
            vec3d = vec3d1;
        }

        vec3d1 = collideWithYPlane(box, box.minY, vecA, vecB);

        if (vec3d1 != null && isClosest(vecA, vecB, vec3d, vec3d1))
        {
            vec3d = vec3d1;
        }

        vec3d1 = collideWithYPlane(box, box.maxY, vecA, vecB);

        if (vec3d1 != null && isClosest(vecA, vecB, vec3d, vec3d1))
        {
            vec3d = vec3d1;
        }

        vec3d1 = collideWithZPlane(box, box.minZ, vecA, vecB);

        if (vec3d1 != null && isClosest(vecA, vecB, vec3d, vec3d1))
        {
            vec3d = vec3d1;
        }

        vec3d1 = collideWithZPlane(box, box.maxZ, vecA, vecB);

        if (vec3d1 != null && isClosest(vecA, vecB, vec3d, vec3d1))
        {
            vec3d = vec3d1;
        }
		
		return vec3d;
	}
	
	protected static Vec3d collideWithXPlane(AxisAlignedBB box, double p_186671_1_, Vec3d p_186671_3_, Vec3d p_186671_4_)
    {
        Vec3d vec3d = getIntermediateWithXValue(p_186671_3_, p_186671_4_, p_186671_1_);
        return clampToBox(box, vec3d);
        //return vec3d != null && box.intersectsWithYZ(vec3d) ? vec3d : null;
    }

	protected static Vec3d collideWithYPlane(AxisAlignedBB box, double p_186663_1_, Vec3d p_186663_3_, Vec3d p_186663_4_)
    {
        Vec3d vec3d = getIntermediateWithYValue(p_186663_3_, p_186663_4_, p_186663_1_);
        return clampToBox(box, vec3d);
        //return vec3d != null && box.intersectsWithXZ(vec3d) ? vec3d : null;
    }

	protected static Vec3d collideWithZPlane(AxisAlignedBB box, double p_186665_1_, Vec3d p_186665_3_, Vec3d p_186665_4_)
    {
        Vec3d vec3d = getIntermediateWithZValue(p_186665_3_, p_186665_4_, p_186665_1_);
        return clampToBox(box, vec3d);
        //return vec3d != null && box.intersectsWithXY(vec3d) ? vec3d : null;
    }
	
	protected static Vec3d clampToBox(AxisAlignedBB box, Vec3d vec)
    {
		return new Vec3d(MathHelper.clamp(vec.x, box.minX, box.maxX), MathHelper.clamp(vec.y, box.minY, box.maxY), MathHelper.clamp(vec.z, box.minZ, box.maxZ));
    }
	
	protected static boolean isClosest(Vec3d line1, Vec3d line2, @Nullable Vec3d p_186661_2_, Vec3d p_186661_3_)
    {
		if(p_186661_2_ == null)
			return true;
		double d1 = dist_to_segment_squared(p_186661_3_, line1, line2);
		double d2 = dist_to_segment_squared(p_186661_2_, line1, line2);
		if(Math.abs(d1-d2) < 0.01)
			return line1.squareDistanceTo(p_186661_3_) < line1.squareDistanceTo(p_186661_2_);
        return d1 < d2;
    }
	
	//Drillgon200: https://stackoverflow.com/questions/849211/shortest-distance-between-a-point-and-a-line-segment
	//Drillgon200: I'm not figuring this out myself.
	protected static double dist_to_segment_squared(Vec3d point, Vec3d linePoint1, Vec3d linePoint2) {
		  double line_dist = linePoint1.squareDistanceTo(linePoint2);
		  if (line_dist == 0) return point.squareDistanceTo(linePoint1);
		  double t = ((point.x - linePoint1.x) * (linePoint2.x - linePoint1.x) + (point.y - linePoint1.y) * (linePoint2.y - linePoint1.y) + (point.z - linePoint1.z) * (linePoint2.z - linePoint1.z)) / line_dist;
		  t = MathHelper.clamp(t, 0, 1);
		  Vec3d pointOnLine = new Vec3d(linePoint1.x + t * (linePoint2.x - linePoint1.x), linePoint1.y + t * (linePoint2.y - linePoint1.y), linePoint1.z + t * (linePoint2.z - linePoint1.z));
		  return point.squareDistanceTo(pointOnLine);
	}
	
	/**
     * Returns a new vector with x value equal to the second parameter, along the line between this vector and the
     * passed in vector, or null if not possible.
     */
    @Nullable
    public static Vec3d getIntermediateWithXValue(Vec3d vec1, Vec3d vec, double x)
    {
        double d0 = vec.x - vec1.x;
        double d1 = vec.y - vec1.y;
        double d2 = vec.z - vec1.z;

        if (d0 * d0 < 1.0000000116860974E-7D)
        {
            return vec;
        }
        else
        {
            double d3 = (x - vec1.x) / d0;
            if(d3 < 0){
            	return new Vec3d(x, vec.y, vec.z);
            } else if(d3 > 1){
            	return new Vec3d(x, vec1.y, vec1.z);
            } else {
            	return new Vec3d(vec1.x + d0 * d3, vec1.y + d1 * d3, vec1.z + d2 * d3);
            }
            //return d3 >= 0.0D && d3 <= 1.0D ? new Vec3d(vec1.x + d0 * d3, vec1.y + d1 * d3, vec1.z + d2 * d3) : null;
        }
    }

    /**
     * Returns a new vector with y value equal to the second parameter, along the line between this vector and the
     * passed in vector, or null if not possible.
     */
    @Nullable
    public static Vec3d getIntermediateWithYValue(Vec3d vec1, Vec3d vec, double y)
    {
        double d0 = vec.x - vec1.x;
        double d1 = vec.y - vec1.y;
        double d2 = vec.z - vec1.z;

        if (d1 * d1 < 1.0000000116860974E-7D)
        {
            return vec;
        }
        else
        {
            double d3 = (y - vec1.y) / d1;
            if(d3 < 0){
            	return new Vec3d(vec.x, y, vec.z);
            } else if(d3 > 1){
            	return new Vec3d(vec1.x, y, vec1.z);
            } else {
            	return new Vec3d(vec1.x + d0 * d3, vec1.y + d1 * d3, vec1.z + d2 * d3);
            }
            //return d3 >= 0.0D && d3 <= 1.0D ? new Vec3d(vec1.x + d0 * d3, vec1.y + d1 * d3, vec1.z + d2 * d3) : null;
        }
    }

    /**
     * Returns a new vector with z value equal to the second parameter, along the line between this vector and the
     * passed in vector, or null if not possible.
     */
    @Nullable
    public static Vec3d getIntermediateWithZValue(Vec3d vec1, Vec3d vec, double z)
    {
        double d0 = vec.x - vec1.x;
        double d1 = vec.y - vec1.y;
        double d2 = vec.z - vec1.z;

        if (d2 * d2 < 1.0000000116860974E-7D)
        {
            return vec;
        }
        else
        {
            double d3 = (z - vec1.z) / d2;
            if(d3 < 0){
            	return new Vec3d(vec.x, vec.y, z);
            } else if(d3 > 1){
            	return new Vec3d(vec1.x, vec1.y, z);
            } else {
            	return new Vec3d(vec1.x + d0 * d3, vec1.y + d1 * d3, vec1.z + d2 * d3);
            }
            //return d3 >= 0.0D && d3 <= 1.0D ? new Vec3d(vec1.x + d0 * d3, vec1.y + d1 * d3, vec1.z + d2 * d3) : null;
        }
    }
    
    public static Vec3d getEuler(Vec3d vec){
    	double yaw = Math.toDegrees(Math.atan2(vec.x, vec.z));
		double sqrt = MathHelper.sqrt(vec.x * vec.x + vec.z * vec.z);
		double pitch = Math.toDegrees(Math.atan2(vec.y, sqrt));
		return new Vec3d(yaw, pitch, 0);
    }
    
    //Drillgon200: https://thebookofshaders.com/glossary/?search=smoothstep
    public static double smoothstep(double t, double edge0, double edge1){
    	t = MathHelper.clamp((t - edge0) / (edge1 - edge0), 0.0, 1.0);
        return t * t * (3.0 - 2.0 * t);
    }
	
	public static Vec3d getPosition(float interpolation, EntityPlayer player) {
		if(interpolation == 1.0F) {
			return new Vec3d(player.posX, player.posY + (player.getEyeHeight() - player.getDefaultEyeHeight()), player.posZ);
		} else {
			double d0 = player.prevPosX + (player.posX - player.prevPosX) * interpolation;
			double d1 = player.prevPosY + (player.posY - player.prevPosY) * interpolation + (player.getEyeHeight() - player.getDefaultEyeHeight());
			double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * interpolation;
			return new Vec3d(d0, d1, d2);
		}
	}

	//TODO: jesus christ
	// Flut-Füll gesteuerter Energieübertragungsalgorithmus
	// Flood fill controlled energy transmission algorithm
	public static void ffgeua(MutableBlockPos pos, boolean newTact, ISource that, World worldObj) {
		Block block = worldObj.getBlockState(pos).getBlock();
		TileEntity tileentity = worldObj.getTileEntity(pos);

		// Factories
		if(block == ModBlocks.factory_titanium_conductor && worldObj.getBlockState(pos.up()).getBlock() == ModBlocks.factory_titanium_core)
		{
			tileentity = worldObj.getTileEntity(pos.up());
		}
		if(block == ModBlocks.factory_titanium_conductor && worldObj.getBlockState(pos.down()).getBlock() == ModBlocks.factory_titanium_core)
		{
			tileentity = worldObj.getTileEntity(pos.down());
		}
		if(block == ModBlocks.factory_advanced_conductor && worldObj.getBlockState(pos.up()).getBlock() == ModBlocks.factory_advanced_core)
		{
			tileentity = worldObj.getTileEntity(pos.up());
		}
		if(block == ModBlocks.factory_advanced_conductor && worldObj.getBlockState(pos.down()).getBlock() == ModBlocks.factory_advanced_core)
		{
			tileentity = worldObj.getTileEntity(pos.down());
		}
		//Derrick
		if(block == ModBlocks.dummy_port_well && worldObj.getBlockState(pos.add(1, 0, 0)).getBlock() == ModBlocks.machine_well)
		{
			tileentity = worldObj.getTileEntity(pos.add(1, 0, 0));
		}
		if(block == ModBlocks.dummy_port_well && worldObj.getBlockState(pos.add(-1, 0, 0)).getBlock() == ModBlocks.machine_well)
		{
			tileentity = worldObj.getTileEntity(pos.add(-1, 0, 0));
		}
		if(block == ModBlocks.dummy_port_well && worldObj.getBlockState(pos.add(0, 0, 1)).getBlock() == ModBlocks.machine_well)
		{
			tileentity = worldObj.getTileEntity(pos.add(0, 0, 1));
		}
		if(block == ModBlocks.dummy_port_well && worldObj.getBlockState(pos.add(0, 0, -1)).getBlock() == ModBlocks.machine_well)
		{
			tileentity = worldObj.getTileEntity(pos.add(0, 0, -1));
		}
		//Mining Drill
		if(block == ModBlocks.dummy_port_drill && worldObj.getBlockState(pos.add(1, 0, 0)).getBlock() == ModBlocks.machine_drill)
		{
			tileentity = worldObj.getTileEntity(pos.add(1, 0, 0));
		}
		if(block == ModBlocks.dummy_port_drill && worldObj.getBlockState(pos.add(-1, 0, 0)).getBlock() == ModBlocks.machine_drill)
		{
			tileentity = worldObj.getTileEntity(pos.add(-1, 0, 0));
		}
		if(block == ModBlocks.dummy_port_drill && worldObj.getBlockState(pos.add(0, 0, 1)).getBlock() == ModBlocks.machine_drill)
		{
			tileentity = worldObj.getTileEntity(pos.add(0, 0, 1));
		}
		if(block == ModBlocks.dummy_port_drill && worldObj.getBlockState(pos.add(0, 0, -1)).getBlock() == ModBlocks.machine_drill)
		{
			tileentity = worldObj.getTileEntity(pos.add(0, 0, -1));
		}
		// Assembler
		if(block == ModBlocks.dummy_port_assembler) {
			tileentity = worldObj.getTileEntity(((TileEntityDummy) worldObj.getTileEntity(pos)).target);
		}
		// Chemplant
		if(block == ModBlocks.dummy_port_chemplant) {
			tileentity = worldObj.getTileEntity(((TileEntityDummy) worldObj.getTileEntity(pos)).target);
		}
		// Refinery
		if(block == ModBlocks.dummy_port_refinery)
		{
			tileentity = worldObj.getTileEntity(((TileEntityDummy)worldObj.getTileEntity(pos)).target);
		}
		//Pumpjack
		if(block == ModBlocks.dummy_port_pumpjack)
		{
			tileentity = worldObj.getTileEntity(((TileEntityDummy)worldObj.getTileEntity(pos)).target);
		}
		//AMS Limiter
		if(block == ModBlocks.dummy_port_ams_limiter)
		{
			tileentity = worldObj.getTileEntity(((TileEntityDummy)worldObj.getTileEntity(pos)).target);
		}
		//AMS Emitter
		if(block == ModBlocks.dummy_port_ams_emitter)
		{
			tileentity = worldObj.getTileEntity(((TileEntityDummy)worldObj.getTileEntity(pos)).target);
		}
		//Launchers
		if(block == ModBlocks.dummy_port_compact_launcher || block == ModBlocks.dummy_port_launch_table)
		{
			tileentity = worldObj.getTileEntity(((TileEntityDummy)worldObj.getTileEntity(pos)).target);
		}

		if(tileentity instanceof IConductor) {
			if(tileentity instanceof TileEntityCable) {
				if(Library.checkUnionList(((TileEntityCable) tileentity).uoteab, that)) {
					for(int i = 0; i < ((TileEntityCable) tileentity).uoteab.size(); i++) {
						if(((TileEntityCable) tileentity).uoteab.get(i).source == that) {
							if(((TileEntityCable) tileentity).uoteab.get(i).ticked != newTact) {
								((TileEntityCable) tileentity).uoteab.get(i).ticked = newTact;
								that.ffgeua(pos.up(), that.getTact());
								that.ffgeua(pos.down(), that.getTact());
								that.ffgeua(pos.west(), that.getTact());
								that.ffgeua(pos.east(), that.getTact());
								that.ffgeua(pos.north(), that.getTact());
								that.ffgeua(pos.south(), that.getTact());
							}
						}
					}
				} else {
					((TileEntityCable) tileentity).uoteab.add(new UnionOfTileEntitiesAndBooleans(that, newTact));
				}
			}
			if(tileentity instanceof TileEntityWireCoated) {
				if(Library.checkUnionList(((TileEntityWireCoated) tileentity).uoteab, that)) {
					for(int i = 0; i < ((TileEntityWireCoated) tileentity).uoteab.size(); i++) {
						if(((TileEntityWireCoated) tileentity).uoteab.get(i).source == that) {
							if(((TileEntityWireCoated) tileentity).uoteab.get(i).ticked != newTact) {
								((TileEntityWireCoated) tileentity).uoteab.get(i).ticked = newTact;
								that.ffgeua(pos.up(), that.getTact());
								that.ffgeua(pos.down(), that.getTact());
								that.ffgeua(pos.west(), that.getTact());
								that.ffgeua(pos.east(), that.getTact());
								that.ffgeua(pos.north(), that.getTact());
								that.ffgeua(pos.south(), that.getTact());
							}
						}
					}
				} else {
					((TileEntityWireCoated) tileentity).uoteab.add(new UnionOfTileEntitiesAndBooleans(that, newTact));
				}
			}
			if(tileentity instanceof TileEntityCableSwitch) {
				if(tileentity.getBlockMetadata() == 1) {
					if(Library.checkUnionList(((TileEntityCableSwitch) tileentity).uoteab, that)) {
						for(int i = 0; i < ((TileEntityCableSwitch) tileentity).uoteab.size(); i++) {
							if(((TileEntityCableSwitch) tileentity).uoteab.get(i).source == that) {
								if(((TileEntityCableSwitch) tileentity).uoteab.get(i).ticked != newTact) {
									((TileEntityCableSwitch) tileentity).uoteab.get(i).ticked = newTact;
									that.ffgeua(pos.up(), that.getTact());
									that.ffgeua(pos.down(), that.getTact());
									that.ffgeua(pos.west(), that.getTact());
									that.ffgeua(pos.east(), that.getTact());
									that.ffgeua(pos.north(), that.getTact());
									that.ffgeua(pos.south(), that.getTact());
								}
							}
						}
					} else {
						((TileEntityCableSwitch) tileentity).uoteab.add(new UnionOfTileEntitiesAndBooleans(that, newTact));
					}
				} else {
					((TileEntityCableSwitch) tileentity).uoteab.clear();
				}
			}
			if(tileentity instanceof TileEntityPylonRedWire) {
				if(Library.checkUnionList(((TileEntityPylonRedWire) tileentity).uoteab, that)) {
					for(int i = 0; i < ((TileEntityPylonRedWire) tileentity).uoteab.size(); i++) {
						if(((TileEntityPylonRedWire) tileentity).uoteab.get(i).source == that) {
							if(((TileEntityPylonRedWire) tileentity).uoteab.get(i).ticked != newTact) {
								((TileEntityPylonRedWire) tileentity).uoteab.get(i).ticked = newTact;
								for(int j = 0; j < ((TileEntityPylonRedWire) tileentity).connected.size(); j++) {
									TileEntityPylonRedWire pylon = ((TileEntityPylonRedWire) tileentity).connected.get(j);
									if(pylon != null) {
										that.ffgeua(pylon.getPos().east(), that.getTact());
										that.ffgeua(pylon.getPos().west(), that.getTact());
										that.ffgeua(pylon.getPos().up(), that.getTact());
										that.ffgeua(pylon.getPos().down(), that.getTact());
										that.ffgeua(pylon.getPos().south(), that.getTact());
										that.ffgeua(pylon.getPos().north(), that.getTact());

										that.ffgeua(pylon.getPos(), that.getTact());
									}
								}
							}
						}
					}
				} else {
					((TileEntityPylonRedWire) tileentity).uoteab.add(new UnionOfTileEntitiesAndBooleans(that, newTact));
				}
			}
		}

		// TE will not be added as consumer if:
		// -TE is the source (will not send to itself)
		// -TE is already full
		// -TE is a battery set to output only
		// -TE as well as source are transformers of the same frequency
		if(tileentity instanceof IConsumer && newTact && !(tileentity instanceof TileEntityMachineBattery && ((TileEntityMachineBattery) tileentity).conducts) && tileentity != that && ((IConsumer) tileentity).getPower() < ((IConsumer) tileentity).getMaxPower() && !(tileentity instanceof TileEntityMachineTransformer && that instanceof TileEntityMachineTransformer && ((TileEntityMachineTransformer) tileentity).delay == ((TileEntityMachineTransformer) that).delay)) {
			that.getList().add((IConsumer) tileentity);
		}

		if(!newTact) {
			int size = that.getList().size();
			if(size > 0) {
				long part = that.getSPower() / size;
				for(IConsumer consume : that.getList()) {
					if(consume.getPower() < consume.getMaxPower()) {
						if(consume.getMaxPower() - consume.getPower() >= part) {
							that.setSPower(that.getSPower() - part);
							consume.setPower(consume.getPower() + part);
						} else {
							that.setSPower(that.getSPower() - (consume.getMaxPower() - consume.getPower()));
							consume.setPower(consume.getMaxPower());
						}
					}
				}
			}
			that.clearList();
		}

	}

	/**
	 * Itemstack equality method except it accounts for possible null stacks and
	 * doesn't check if empty
	 */
	public static boolean areItemsEqual(ItemStack stackA, ItemStack stackB) {
		if(stackA == null & stackB == null)
			return true;
		else if((stackA == null && stackB != null) || (stackA != null && stackB == null))
			return false;
		else
			return stackA.getMetadata() == stackB.getMetadata() && stackA.getItem() == stackB.getItem();
	}
	
	public static boolean checkCableConnectables(World world, int x, int y, int z) {
		return checkCableConnectables(world, new BlockPos(x, y, z));
	}

	public static boolean checkCableConnectables(World world, BlockPos pos) {
		TileEntity tileentity = world.getTileEntity(pos);
		Block b = world.getBlockState(pos).getBlock();
		if((tileentity != null && (tileentity instanceof IConductor || tileentity instanceof IConsumer || tileentity instanceof ISource)) ||
				 b == ModBlocks.fusion_center ||
				 b == ModBlocks.factory_titanium_conductor ||
				 b == ModBlocks.factory_advanced_conductor ||
				 b == ModBlocks.watz_conductor ||
				 b == ModBlocks.fwatz_hatch ||
				 b == ModBlocks.dummy_port_cyclotron ||
				 b == ModBlocks.dummy_port_well ||
				 b == ModBlocks.dummy_port_flare ||
				 b == ModBlocks.dummy_port_drill ||
		b == ModBlocks.dummy_port_assembler || b == ModBlocks.dummy_port_chemplant ||
		 b == ModBlocks.dummy_port_refinery ||
		 b == ModBlocks.dummy_port_pumpjack ||
		 b == ModBlocks.dummy_port_turbofan ||
		 b == ModBlocks.dummy_port_ams_limiter ||
		 b == ModBlocks.dummy_port_ams_emitter ||
		 b == ModBlocks.dummy_port_ams_base ||
		 b == ModBlocks.dummy_port_radgen ||
		 b == ModBlocks.dummy_port_compact_launcher ||
		 b == ModBlocks.dummy_port_launch_table
		) {
			return true;
		}
		return false;
	}

	public static boolean checkUnionList(List<UnionOfTileEntitiesAndBooleans> list, ISource that) {

		for(UnionOfTileEntitiesAndBooleans union : list) {
			if(union.source == that) {
				return true;
			}
		}

		return false;
	}

	public static boolean hasInventoryItem(InventoryPlayer inventory, Item ammo) {
		for(int i = 0; i < inventory.getSizeInventory(); i++) {
			ItemStack stack = inventory.getStackInSlot(i);
			if(stack.getItem() == ammo) {
				return true;
			}
		}
		return false;
	}

	public static void consumeInventoryItem(InventoryPlayer inventory, Item ammo) {
		for(int i = 0; i < inventory.getSizeInventory(); i++) {
			ItemStack stack = inventory.getStackInSlot(i);
			if(stack.getItem() == ammo && !stack.isEmpty()) {
				stack.shrink(1);
				inventory.setInventorySlotContents(i, stack.copy());
				return;
			}
		}
	}

	//////  //////  //////  //////  //////  ////        //////  //////  //////
	//      //  //  //        //    //      //  //      //      //      //    
	////    //////  /////     //    ////    ////        ////    //  //  //  //
	//      //  //     //     //    //      //  //      //      //  //  //  //
	//////  //  //  /////     //    //////  //  //      //////  //////  //////

	public static EntityLivingBase getClosestEntityForChopper(World world, double x, double y, double z, double radius) {
		double d4 = -1.0D;
		EntityLivingBase entityplayer = null;

		for (int i = 0; i < world.loadedEntityList.size(); ++i) {
			if (world.loadedEntityList.get(i) instanceof EntityLivingBase && !(world.loadedEntityList.get(i) instanceof EntityHunterChopper)) {
				EntityLivingBase entityplayer1 = (EntityLivingBase) world.loadedEntityList.get(i);

				if (entityplayer1.isEntityAlive() && !(entityplayer1 instanceof EntityPlayer && ((EntityPlayer)entityplayer1).capabilities.disableDamage)) {
					double d5 = entityplayer1.getDistanceSq(x, y, z);
					double d6 = radius;

					if (entityplayer1.isSneaking()) {
						d6 = radius * 0.800000011920929D;
					}

					if ((radius < 0.0D || d5 < d6 * d6) && (d4 == -1.0D || d5 < d4)) {
						d4 = d5;
						entityplayer = entityplayer1;
					}
				}
			}
		}

		return entityplayer;
	}
	
	//Drillgon200: Loot tables? I don't have time for that!
	public static void generateChestContents(Random p_76293_0_, WeightedRandomChestContentFrom1710[] p_76293_1_, ICapabilityProvider p_76293_2_, int p_76293_3_)
    {
		if(p_76293_2_.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)){
			IItemHandler test = p_76293_2_.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
			if(test instanceof IItemHandlerModifiable){
				IItemHandlerModifiable inventory = (IItemHandlerModifiable)test;
				
				for (int j = 0; j < p_76293_3_; ++j)
		        {
					WeightedRandomChestContentFrom1710 weightedrandomchestcontent = (WeightedRandomChestContentFrom1710)WeightedRandom.getRandomItem(p_76293_0_, Arrays.asList(p_76293_1_));
		            ItemStack[] stacks = weightedrandomchestcontent.generateChestContent(p_76293_0_, inventory);

		            for (ItemStack item : stacks)
		            {
		            	inventory.setStackInSlot(p_76293_0_.nextInt(inventory.getSlots()), item);
		            }
		        }
			}
		}
        
    }
	
	public static Block getRandomConcrete() {
		int i = rand.nextInt(100);

		if(i < 5)
			return ModBlocks.brick_concrete_broken;
		if(i < 20)
			return ModBlocks.brick_concrete_cracked;
		if(i < 50)
			return ModBlocks.brick_concrete_mossy;
		
		return ModBlocks.brick_concrete;
	}
	
	public static void placeDoorWithoutCheck(World worldIn, BlockPos pos, EnumFacing facing, Block door, boolean isRightHinge)
    {
        BlockPos blockpos2 = pos.up();
        boolean flag2 = worldIn.isBlockPowered(pos) || worldIn.isBlockPowered(blockpos2);
        IBlockState iblockstate = door.getDefaultState().withProperty(BlockDoor.FACING, facing).withProperty(BlockDoor.HINGE, isRightHinge ? BlockDoor.EnumHingePosition.RIGHT : BlockDoor.EnumHingePosition.LEFT).withProperty(BlockDoor.POWERED, Boolean.valueOf(flag2)).withProperty(BlockDoor.OPEN, Boolean.valueOf(flag2));
        worldIn.setBlockState(pos, iblockstate.withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER), 2);
        worldIn.setBlockState(blockpos2, iblockstate.withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER), 2);
        worldIn.notifyNeighborsOfStateChange(pos, door, false);
        worldIn.notifyNeighborsOfStateChange(blockpos2, door, false);
    }
	
	public static boolean areItemStacksEqualIgnoreCount(ItemStack a, ItemStack b){
		if (a.isEmpty() && b.isEmpty())
        {
            return true;
        }
        else
        {
            if(!a.isEmpty() && !b.isEmpty()){

                if (a.getItem() != b.getItem())
                {
                    return false;
                }
                else if (a.getMetadata() != b.getMetadata())
                {
                    return false;
                }
                else if (a.getTagCompound() == null && b.getTagCompound() != null)
                {
                    return false;
                }
                else
                {
                    return (a.getTagCompound() == null || a.getTagCompound().equals(b.getTagCompound())) && a.areCapsCompatible(b);
                }
            }
        }
		return false;
	}
	
	/**
	 * Same as ItemStack.areItemStacksEqual, except the second one's tag only has to contain all the first one's tag, rather than being exactly equal.
	 */
	public static boolean areItemStacksCompatible(ItemStack base, ItemStack toTest, boolean shouldCompareSize){
		if (base.isEmpty() && toTest.isEmpty())
        {
            return true;
        }
        else
        {
            if(!base.isEmpty() && !toTest.isEmpty()){

            	if(shouldCompareSize && base.getCount() != toTest.getCount()){
            		return false;
            	} 
            	else if (base.getItem() != toTest.getItem())
                {
                    return false;
                }
                else if (base.getMetadata() != toTest.getMetadata() && !(base.getMetadata() == OreDictionary.WILDCARD_VALUE))
                {
                    return false;
                }
                else if (base.getTagCompound() == null && toTest.getTagCompound() != null)
                {
                    return false;
                }
                else
                {
                    return (base.getTagCompound() == null || tagContainsOther(base.getTagCompound(), toTest.getTagCompound())) && base.areCapsCompatible(toTest);
                }
            }
        }
		return false;
	}
	
	public static boolean areItemStacksCompatible(ItemStack base, ItemStack toTest){
		return areItemStacksCompatible(base, toTest, true);
	}
	
	/**
	 * Returns true if the second compound contains all the tags and values of the first one, but it can have more. This helps with intermod compatibility
	 */
	public static boolean tagContainsOther(NBTTagCompound tester, NBTTagCompound container){
		if(tester == null && container == null){
			return true;
		} if(tester == null ^ container == null){
			return false;
		} else {
			for(String s : tester.getKeySet()){
				if(!container.hasKey(s)){
					return false;
				} else {
					NBTBase nbt1 = tester.getTag(s);
					NBTBase nbt2 = container.getTag(s);
					if(nbt1 instanceof NBTTagCompound && nbt2 instanceof NBTTagCompound){
						if(!tagContainsOther((NBTTagCompound)nbt1, (NBTTagCompound) nbt2))
							return false;
					} else {
						if(!nbt1.equals(nbt2))
							return false;
					}
				}
			}
		}
		return true;
	}
	
	public static List<int[]> getBlockPosInPath(BlockPos pos, int length, Vec3 vec0) {
		List<int[]> list = new ArrayList<int[]>();
		
		for(int i = 0; i <= length; i++) {
			list.add(new int[] { (int)(pos.getX() + (vec0.xCoord * i)), pos.getY(), (int)(pos.getZ() + (vec0.zCoord * i)), i });
		}
		
		return list;
	}

	public static List<ItemStack> copyItemStackList(List<ItemStack> inputs) {
		List<ItemStack> list = new ArrayList<ItemStack>();
		inputs.forEach(stack -> {list.add(stack.copy());});
		return list;
	}
	
	public static IEntityRadioactive getEntRadCap(Entity e){
		if(e.hasCapability(EntityRadiationProvider.ENT_RAD_CAP, null))
			return e.getCapability(EntityRadiationProvider.ENT_RAD_CAP, null);
		return EntityRadiationProvider.DUMMY;
	}

	public static void addToInventoryOrDrop(EntityPlayer player, ItemStack stack) {
		if(!player.inventory.addItemStackToInventory(stack)){
			player.dropItem(stack, false, false);
		}
	}
	
}