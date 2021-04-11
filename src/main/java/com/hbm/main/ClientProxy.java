package com.hbm.main;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

import com.hbm.animloader.AnimationWrapper.EndResult;
import com.hbm.animloader.AnimationWrapper.EndType;
import com.hbm.blocks.BlockDummyable;
import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.generic.BlockModDoor;
import com.hbm.blocks.generic.EntityGrenadeTau;
import com.hbm.blocks.generic.TrappedBrick;
import com.hbm.blocks.machine.BlockSeal;
import com.hbm.config.GeneralConfig;
import com.hbm.entity.effect.EntityBlackHole;
import com.hbm.entity.effect.EntityCloudFleija;
import com.hbm.entity.effect.EntityCloudFleijaRainbow;
import com.hbm.entity.effect.EntityCloudSolinium;
import com.hbm.entity.effect.EntityCloudTom;
import com.hbm.entity.effect.EntityEMPBlast;
import com.hbm.entity.effect.EntityFalloutRain;
import com.hbm.entity.effect.EntityNukeCloudBig;
import com.hbm.entity.effect.EntityNukeCloudNoShroom;
import com.hbm.entity.effect.EntityNukeCloudSmall;
import com.hbm.entity.effect.EntityRagingVortex;
import com.hbm.entity.effect.EntityVortex;
import com.hbm.entity.grenade.EntityGrenadeASchrab;
import com.hbm.entity.grenade.EntityGrenadeBlackHole;
import com.hbm.entity.grenade.EntityGrenadeBreach;
import com.hbm.entity.grenade.EntityGrenadeBurst;
import com.hbm.entity.grenade.EntityGrenadeCloud;
import com.hbm.entity.grenade.EntityGrenadeCluster;
import com.hbm.entity.grenade.EntityGrenadeElectric;
import com.hbm.entity.grenade.EntityGrenadeFire;
import com.hbm.entity.grenade.EntityGrenadeFlare;
import com.hbm.entity.grenade.EntityGrenadeFrag;
import com.hbm.entity.grenade.EntityGrenadeGas;
import com.hbm.entity.grenade.EntityGrenadeGascan;
import com.hbm.entity.grenade.EntityGrenadeGeneric;
import com.hbm.entity.grenade.EntityGrenadeIFBouncy;
import com.hbm.entity.grenade.EntityGrenadeIFBrimstone;
import com.hbm.entity.grenade.EntityGrenadeIFConcussion;
import com.hbm.entity.grenade.EntityGrenadeIFGeneric;
import com.hbm.entity.grenade.EntityGrenadeIFHE;
import com.hbm.entity.grenade.EntityGrenadeIFHopwire;
import com.hbm.entity.grenade.EntityGrenadeIFImpact;
import com.hbm.entity.grenade.EntityGrenadeIFIncendiary;
import com.hbm.entity.grenade.EntityGrenadeIFMystery;
import com.hbm.entity.grenade.EntityGrenadeIFNull;
import com.hbm.entity.grenade.EntityGrenadeIFSpark;
import com.hbm.entity.grenade.EntityGrenadeIFSticky;
import com.hbm.entity.grenade.EntityGrenadeIFToxic;
import com.hbm.entity.grenade.EntityGrenadeLemon;
import com.hbm.entity.grenade.EntityGrenadeMIRV;
import com.hbm.entity.grenade.EntityGrenadeMk2;
import com.hbm.entity.grenade.EntityGrenadeNuclear;
import com.hbm.entity.grenade.EntityGrenadeNuke;
import com.hbm.entity.grenade.EntityGrenadePC;
import com.hbm.entity.grenade.EntityGrenadePlasma;
import com.hbm.entity.grenade.EntityGrenadePoison;
import com.hbm.entity.grenade.EntityGrenadePulse;
import com.hbm.entity.grenade.EntityGrenadeSchrabidium;
import com.hbm.entity.grenade.EntityGrenadeShrapnel;
import com.hbm.entity.grenade.EntityGrenadeSmart;
import com.hbm.entity.grenade.EntityGrenadeStrong;
import com.hbm.entity.grenade.EntityGrenadeZOMG;
import com.hbm.entity.item.EntityFireworks;
import com.hbm.entity.item.EntityMovingItem;
import com.hbm.entity.logic.EntityBlast;
import com.hbm.entity.logic.EntityBomber;
import com.hbm.entity.logic.EntityDeathBlast;
import com.hbm.entity.logic.EntityEMP;
import com.hbm.entity.logic.EntityNukeExplosionMK3;
import com.hbm.entity.logic.EntityNukeExplosionMK4;
import com.hbm.entity.logic.EntityNukeExplosionPlus;
import com.hbm.entity.logic.EntityTomBlast;
import com.hbm.entity.missile.EntityBobmazon;
import com.hbm.entity.missile.EntityBombletSelena;
import com.hbm.entity.missile.EntityBombletTheta;
import com.hbm.entity.missile.EntityBooster;
import com.hbm.entity.missile.EntityCarrier;
import com.hbm.entity.missile.EntityMIRV;
import com.hbm.entity.missile.EntityMinerRocket;
import com.hbm.entity.missile.EntityMissileAntiBallistic;
import com.hbm.entity.missile.EntityMissileBHole;
import com.hbm.entity.missile.EntityMissileBunkerBuster;
import com.hbm.entity.missile.EntityMissileBurst;
import com.hbm.entity.missile.EntityMissileBusterStrong;
import com.hbm.entity.missile.EntityMissileCluster;
import com.hbm.entity.missile.EntityMissileClusterStrong;
import com.hbm.entity.missile.EntityMissileCustom;
import com.hbm.entity.missile.EntityMissileDoomsday;
import com.hbm.entity.missile.EntityMissileDrill;
import com.hbm.entity.missile.EntityMissileEMP;
import com.hbm.entity.missile.EntityMissileEMPStrong;
import com.hbm.entity.missile.EntityMissileEndo;
import com.hbm.entity.missile.EntityMissileExo;
import com.hbm.entity.missile.EntityMissileGeneric;
import com.hbm.entity.missile.EntityMissileIncendiary;
import com.hbm.entity.missile.EntityMissileIncendiaryStrong;
import com.hbm.entity.missile.EntityMissileInferno;
import com.hbm.entity.missile.EntityMissileMicro;
import com.hbm.entity.missile.EntityMissileMirv;
import com.hbm.entity.missile.EntityMissileNuclear;
import com.hbm.entity.missile.EntityMissileRain;
import com.hbm.entity.missile.EntityMissileSchrabidium;
import com.hbm.entity.missile.EntityMissileStrong;
import com.hbm.entity.missile.EntityMissileTaint;
import com.hbm.entity.missile.EntitySoyuz;
import com.hbm.entity.missile.EntitySoyuzCapsule;
import com.hbm.entity.mob.EntityCyberCrab;
import com.hbm.entity.mob.EntityDuck;
import com.hbm.entity.mob.EntityFBI;
import com.hbm.entity.mob.EntityHunterChopper;
import com.hbm.entity.mob.EntityMaskMan;
import com.hbm.entity.mob.EntityNuclearCreeper;
import com.hbm.entity.mob.EntityQuackos;
import com.hbm.entity.mob.EntityRADBeast;
import com.hbm.entity.mob.EntityTaintCrab;
import com.hbm.entity.mob.EntityTaintedCreeper;
import com.hbm.entity.mob.EntityTeslaCrab;
import com.hbm.entity.mob.botprime.EntityBOTPrimeBody;
import com.hbm.entity.mob.botprime.EntityBOTPrimeHead;
import com.hbm.entity.mob.sodtekhnologiyah.EntityBallsOTronSegment;
import com.hbm.entity.particle.EntityBSmokeFX;
import com.hbm.entity.particle.EntityChlorineFX;
import com.hbm.entity.particle.EntityCloudFX;
import com.hbm.entity.particle.EntityDSmokeFX;
import com.hbm.entity.particle.EntityFogFX;
import com.hbm.entity.particle.EntityGasFX;
import com.hbm.entity.particle.EntityGasFlameFX;
import com.hbm.entity.particle.EntityOilSpillFX;
import com.hbm.entity.particle.EntityOrangeFX;
import com.hbm.entity.particle.EntityPinkCloudFX;
import com.hbm.entity.particle.EntitySSmokeFX;
import com.hbm.entity.particle.EntitySmokeFX;
import com.hbm.entity.particle.EntityTSmokeFX;
import com.hbm.entity.particle.ParticleContrail;
import com.hbm.entity.projectile.EntityAAShell;
import com.hbm.entity.projectile.EntityBaleflare;
import com.hbm.entity.projectile.EntityBeamVortex;
import com.hbm.entity.projectile.EntityBombletZeta;
import com.hbm.entity.projectile.EntityBoxcar;
import com.hbm.entity.projectile.EntityBuilding;
import com.hbm.entity.projectile.EntityBullet;
import com.hbm.entity.projectile.EntityBulletBase;
import com.hbm.entity.projectile.EntityBurningFOEQ;
import com.hbm.entity.projectile.EntityChopperMine;
import com.hbm.entity.projectile.EntityCombineBall;
import com.hbm.entity.projectile.EntityDischarge;
import com.hbm.entity.projectile.EntityDuchessGambit;
import com.hbm.entity.projectile.EntityExplosiveBeam;
import com.hbm.entity.projectile.EntityFallingNuke;
import com.hbm.entity.projectile.EntityFire;
import com.hbm.entity.projectile.EntityLN2;
import com.hbm.entity.projectile.EntityLaser;
import com.hbm.entity.projectile.EntityLaserBeam;
import com.hbm.entity.projectile.EntityMeteor;
import com.hbm.entity.projectile.EntityMinerBeam;
import com.hbm.entity.projectile.EntityMiniMIRV;
import com.hbm.entity.projectile.EntityMiniNuke;
import com.hbm.entity.projectile.EntityModBeam;
import com.hbm.entity.projectile.EntityOilSpill;
import com.hbm.entity.projectile.EntityPlasmaBeam;
import com.hbm.entity.projectile.EntityRailgunBlast;
import com.hbm.entity.projectile.EntityRainbow;
import com.hbm.entity.projectile.EntityRocket;
import com.hbm.entity.projectile.EntityRocketHoming;
import com.hbm.entity.projectile.EntityRubble;
import com.hbm.entity.projectile.EntitySchrab;
import com.hbm.entity.projectile.EntityShrapnel;
import com.hbm.entity.projectile.EntitySparkBeam;
import com.hbm.entity.projectile.EntityTom;
import com.hbm.entity.projectile.EntityWaterSplash;
import com.hbm.handler.BobmazonOfferFactory;
import com.hbm.handler.HbmKeybinds.EnumKeybind;
import com.hbm.handler.HbmShaderManager;
import com.hbm.handler.JetpackHandler;
import com.hbm.items.ModItems;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.lib.RecoilHandler;
import com.hbm.lib.RefStrings;
import com.hbm.particle.ParticleExSmoke;
import com.hbm.particle.ParticleHadron;
import com.hbm.particle.ParticleLetter;
import com.hbm.particle.ParticleMukeFlash;
import com.hbm.particle.ParticleMukeWave;
import com.hbm.particle.ParticleRadiationFog;
import com.hbm.particle.ParticleRenderLayer;
import com.hbm.particle.ParticleRocketFlame;
import com.hbm.particle.ParticleSmokePlume;
import com.hbm.particle.ParticleSpark;
import com.hbm.particle.bfg.ParticleBFGBeam;
import com.hbm.particle.bfg.ParticleBFGCoreLightning;
import com.hbm.particle.bfg.ParticleBFGParticle;
import com.hbm.particle.bfg.ParticleBFGPrefire;
import com.hbm.particle.bfg.ParticleBFGRing;
import com.hbm.particle.bfg.ParticleBFGShockwave;
import com.hbm.particle.bfg.ParticleBFGSmoke;
import com.hbm.particle_instanced.InstancedParticleRenderer;
import com.hbm.particle_instanced.ParticleContrailInstanced;
import com.hbm.particle_instanced.ParticleExSmokeInstanced;
import com.hbm.particle_instanced.ParticleRocketFlameInstanced;
import com.hbm.render.amlfrom1710.AdvancedModelLoader;
import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.render.anim.BusAnimation;
import com.hbm.render.anim.BusAnimationKeyframe;
import com.hbm.render.anim.BusAnimationSequence;
import com.hbm.render.anim.HbmAnimations;
import com.hbm.render.anim.HbmAnimations.Animation;
import com.hbm.render.anim.HbmAnimations.BlenderAnimation;
import com.hbm.render.entity.ElectricityRenderer;
import com.hbm.render.entity.GasFlameRenderer;
import com.hbm.render.entity.GasRenderer;
import com.hbm.render.entity.RenderAAShell;
import com.hbm.render.entity.RenderBaleflare;
import com.hbm.render.entity.RenderBeam;
import com.hbm.render.entity.RenderBeam2;
import com.hbm.render.entity.RenderBeam3;
import com.hbm.render.entity.RenderBeam4;
import com.hbm.render.entity.RenderBeam5;
import com.hbm.render.entity.RenderBeam6;
import com.hbm.render.entity.RenderBigNuke;
import com.hbm.render.entity.RenderBlackHole;
import com.hbm.render.entity.RenderBoat;
import com.hbm.render.entity.RenderBobmazon;
import com.hbm.render.entity.RenderBomber;
import com.hbm.render.entity.RenderBombletSelena;
import com.hbm.render.entity.RenderBombletTheta;
import com.hbm.render.entity.RenderBombletZeta;
import com.hbm.render.entity.RenderBoxcar;
import com.hbm.render.entity.RenderBuilding;
import com.hbm.render.entity.RenderBullet;
import com.hbm.render.entity.RenderBulletMk2;
import com.hbm.render.entity.RenderChopperMine;
import com.hbm.render.entity.RenderCloudFleija;
import com.hbm.render.entity.RenderCloudRainbow;
import com.hbm.render.entity.RenderCloudSolinium;
import com.hbm.render.entity.RenderCyberCrab;
import com.hbm.render.entity.RenderDeathBlast;
import com.hbm.render.entity.RenderEMPBlast;
import com.hbm.render.entity.RenderEmpty;
import com.hbm.render.entity.RenderFallingNuke;
import com.hbm.render.entity.RenderFireProjectile;
import com.hbm.render.entity.RenderFlare;
import com.hbm.render.entity.RenderGrenade;
import com.hbm.render.entity.RenderHunterChopper;
import com.hbm.render.entity.RenderLN2;
import com.hbm.render.entity.RenderLaser;
import com.hbm.render.entity.RenderMeteor;
import com.hbm.render.entity.RenderMinerRocket;
import com.hbm.render.entity.RenderMiniMIRV;
import com.hbm.render.entity.RenderMiniNuke;
import com.hbm.render.entity.RenderMirv;
import com.hbm.render.entity.RenderNoCloud;
import com.hbm.render.entity.RenderNukeMK4;
import com.hbm.render.entity.RenderRagingVortex;
import com.hbm.render.entity.RenderRainbow;
import com.hbm.render.entity.RenderRocket;
import com.hbm.render.entity.RenderSRocket;
import com.hbm.render.entity.RenderShrapnel;
import com.hbm.render.entity.RenderTom;
import com.hbm.render.entity.RenderVortex;
import com.hbm.render.entity.RenderWormBody;
import com.hbm.render.entity.RenderWormHead;
import com.hbm.render.entity.SpillRenderer;
import com.hbm.render.entity.TSmokeRenderer;
import com.hbm.render.entity.effect.RenderCloudTom;
import com.hbm.render.entity.effect.RenderSmallNukeMK4;
import com.hbm.render.entity.item.RenderMovingItem;
import com.hbm.render.entity.missile.RenderBoosterMissile;
import com.hbm.render.entity.missile.RenderCarrierMissile;
import com.hbm.render.entity.missile.RenderMissileAB;
import com.hbm.render.entity.missile.RenderMissileBHole;
import com.hbm.render.entity.missile.RenderMissileBunkerBuster;
import com.hbm.render.entity.missile.RenderMissileBurst;
import com.hbm.render.entity.missile.RenderMissileBusterStrong;
import com.hbm.render.entity.missile.RenderMissileCluster;
import com.hbm.render.entity.missile.RenderMissileClusterStrong;
import com.hbm.render.entity.missile.RenderMissileCustom;
import com.hbm.render.entity.missile.RenderMissileDoomsday;
import com.hbm.render.entity.missile.RenderMissileDrill;
import com.hbm.render.entity.missile.RenderMissileEMP;
import com.hbm.render.entity.missile.RenderMissileEMPStrong;
import com.hbm.render.entity.missile.RenderMissileEndo;
import com.hbm.render.entity.missile.RenderMissileExo;
import com.hbm.render.entity.missile.RenderMissileGeneric;
import com.hbm.render.entity.missile.RenderMissileIncendiary;
import com.hbm.render.entity.missile.RenderMissileIncendiaryStrong;
import com.hbm.render.entity.missile.RenderMissileInferno;
import com.hbm.render.entity.missile.RenderMissileMicro;
import com.hbm.render.entity.missile.RenderMissileMirv;
import com.hbm.render.entity.missile.RenderMissileNuclear;
import com.hbm.render.entity.missile.RenderMissileRain;
import com.hbm.render.entity.missile.RenderMissileSchrabidium;
import com.hbm.render.entity.missile.RenderMissileStrong;
import com.hbm.render.entity.missile.RenderMissileTaint;
import com.hbm.render.entity.missile.RenderSoyuz;
import com.hbm.render.entity.missile.RenderSoyuzCapsule;
import com.hbm.render.entity.mob.RenderBalls;
import com.hbm.render.entity.mob.RenderDuck;
import com.hbm.render.entity.mob.RenderFBI;
import com.hbm.render.entity.mob.RenderMaskMan;
import com.hbm.render.entity.mob.RenderQuacc;
import com.hbm.render.entity.mob.RenderRADBeast;
import com.hbm.render.entity.mob.RenderTaintCrab;
import com.hbm.render.entity.mob.RenderTeslaCrab;
import com.hbm.render.entity.projectile.RenderVortexBeam;
import com.hbm.render.factories.MultiCloudRendererFactory;
import com.hbm.render.factories.RenderBurningFOEQFactory;
import com.hbm.render.factories.RenderFalloutRainFactory;
import com.hbm.render.factories.RenderFogRenderFactory;
import com.hbm.render.factories.RenderNuclearCreeperFactory;
import com.hbm.render.factories.RenderRubbleFactory;
import com.hbm.render.factories.RenderSSmokeFactory;
import com.hbm.render.factories.RenderTaintedCreeperFactory;
import com.hbm.render.factories.ShrapnelRendererFactory;
import com.hbm.render.item.AssemblyTemplateRender;
import com.hbm.render.item.ChemTemplateRender;
import com.hbm.render.item.FFIdentifierRender;
import com.hbm.render.item.FluidBarrelRender;
import com.hbm.render.item.FluidCanisterRender;
import com.hbm.render.item.FluidTankRender;
import com.hbm.render.item.ItemRenderBase;
import com.hbm.render.item.ItemRenderCell;
import com.hbm.render.item.ItemRenderFFFluidDuct;
import com.hbm.render.item.ItemRenderFluidIcon;
import com.hbm.render.item.ItemRenderGasCanister;
import com.hbm.render.item.ItemRenderLibrary;
import com.hbm.render.item.ItemRenderMissile;
import com.hbm.render.item.ItemRenderMissilePart;
import com.hbm.render.item.ItemRenderMultitool;
import com.hbm.render.item.ItemRenderObj;
import com.hbm.render.item.ItemRenderShim;
import com.hbm.render.item.ItemRendererHot;
import com.hbm.render.item.ItemRendererMachine;
import com.hbm.render.item.ItemRendererMeteorSword;
import com.hbm.render.item.weapon.GunRevolverRender;
import com.hbm.render.item.weapon.ItemRedstoneSwordRender;
import com.hbm.render.item.weapon.ItemRenderBFLauncher;
import com.hbm.render.item.weapon.ItemRenderBigSword;
import com.hbm.render.item.weapon.ItemRenderBullshit;
import com.hbm.render.item.weapon.ItemRenderCCPlasmaCannon;
import com.hbm.render.item.weapon.ItemRenderCalamity;
import com.hbm.render.item.weapon.ItemRenderCrucible;
import com.hbm.render.item.weapon.ItemRenderCryolator;
import com.hbm.render.item.weapon.ItemRenderEMPRay;
import com.hbm.render.item.weapon.ItemRenderEuthanasia;
import com.hbm.render.item.weapon.ItemRenderFatMan;
import com.hbm.render.item.weapon.ItemRenderFolly;
import com.hbm.render.item.weapon.ItemRenderGavel;
import com.hbm.render.item.weapon.ItemRenderGunAnim;
import com.hbm.render.item.weapon.ItemRenderGunAnim2;
import com.hbm.render.item.weapon.ItemRenderGunDefab;
import com.hbm.render.item.weapon.ItemRenderGunHP;
import com.hbm.render.item.weapon.ItemRenderGunJack;
import com.hbm.render.item.weapon.ItemRenderGunSaturnite;
import com.hbm.render.item.weapon.ItemRenderGunSonata;
import com.hbm.render.item.weapon.ItemRenderHFSword;
import com.hbm.render.item.weapon.ItemRenderHSSword;
import com.hbm.render.item.weapon.ItemRenderImmolator;
import com.hbm.render.item.weapon.ItemRenderMIRVLauncher;
import com.hbm.render.item.weapon.ItemRenderMP;
import com.hbm.render.item.weapon.ItemRenderMP40;
import com.hbm.render.item.weapon.ItemRenderMinigun;
import com.hbm.render.item.weapon.ItemRenderOSIPR;
import com.hbm.render.item.weapon.ItemRenderOverkill;
import com.hbm.render.item.weapon.ItemRenderRevolverCursed;
import com.hbm.render.item.weapon.ItemRenderRevolverGold;
import com.hbm.render.item.weapon.ItemRenderRevolverInverted;
import com.hbm.render.item.weapon.ItemRenderRevolverIron;
import com.hbm.render.item.weapon.ItemRenderRevolverLead;
import com.hbm.render.item.weapon.ItemRenderRevolverNightmare;
import com.hbm.render.item.weapon.ItemRenderRevolverSaturnite;
import com.hbm.render.item.weapon.ItemRenderRevolverSchrabidium;
import com.hbm.render.item.weapon.ItemRenderRpg;
import com.hbm.render.item.weapon.ItemRenderStinger;
import com.hbm.render.item.weapon.ItemRenderUboinik;
import com.hbm.render.item.weapon.ItemRenderUzi;
import com.hbm.render.item.weapon.ItemRenderWeaponBolter;
import com.hbm.render.item.weapon.ItemRenderWeaponObj;
import com.hbm.render.item.weapon.ItemRenderWeaponQuadro;
import com.hbm.render.item.weapon.ItemRenderWeaponSauer;
import com.hbm.render.item.weapon.ItemRenderWeaponShotty;
import com.hbm.render.item.weapon.ItemRenderWeaponThompson;
import com.hbm.render.item.weapon.ItemRenderWeaponVortex;
import com.hbm.render.item.weapon.ItemRenderXVL1456;
import com.hbm.render.item.weapon.ItemRenderZOMG;
import com.hbm.render.item.weapon.RenderGunB93;
import com.hbm.render.misc.MissilePart;
import com.hbm.render.tileentity.RenderAMSBase;
import com.hbm.render.tileentity.RenderAMSEmitter;
import com.hbm.render.tileentity.RenderAMSLimiter;
import com.hbm.render.tileentity.RenderAssembler;
import com.hbm.render.tileentity.RenderBigTurbine;
import com.hbm.render.tileentity.RenderBlastDoor;
import com.hbm.render.tileentity.RenderBombMulti;
import com.hbm.render.tileentity.RenderBreeder;
import com.hbm.render.tileentity.RenderBroadcaster;
import com.hbm.render.tileentity.RenderCIWSTurret;
import com.hbm.render.tileentity.RenderCable;
import com.hbm.render.tileentity.RenderCapsule;
import com.hbm.render.tileentity.RenderCentrifuge;
import com.hbm.render.tileentity.RenderCheapoTurret;
import com.hbm.render.tileentity.RenderChemplant;
import com.hbm.render.tileentity.RenderCloudResidue;
import com.hbm.render.tileentity.RenderCompactLauncher;
import com.hbm.render.tileentity.RenderCore;
import com.hbm.render.tileentity.RenderCoreComponent;
import com.hbm.render.tileentity.RenderCrashedBomb;
import com.hbm.render.tileentity.RenderCrystallizer;
import com.hbm.render.tileentity.RenderCyclotron;
import com.hbm.render.tileentity.RenderDecoBlock;
import com.hbm.render.tileentity.RenderDecoBlockAlt;
import com.hbm.render.tileentity.RenderDerrick;
import com.hbm.render.tileentity.RenderEPress;
import com.hbm.render.tileentity.RenderFENSU;
import com.hbm.render.tileentity.RenderFlamerTurret;
import com.hbm.render.tileentity.RenderFluidBarrel;
import com.hbm.render.tileentity.RenderFluidDuct;
import com.hbm.render.tileentity.RenderFluidDuctMk2;
import com.hbm.render.tileentity.RenderFluidTank;
import com.hbm.render.tileentity.RenderGasCent;
import com.hbm.render.tileentity.RenderGasDuct;
import com.hbm.render.tileentity.RenderGasFlare;
import com.hbm.render.tileentity.RenderGeiger;
import com.hbm.render.tileentity.RenderHeavyTurret;
import com.hbm.render.tileentity.RenderIGenerator;
import com.hbm.render.tileentity.RenderITER;
import com.hbm.render.tileentity.RenderITERMultiblock;
import com.hbm.render.tileentity.RenderKeypadBase;
import com.hbm.render.tileentity.RenderLandmine;
import com.hbm.render.tileentity.RenderLaserMiner;
import com.hbm.render.tileentity.RenderLaunchPadTier1;
import com.hbm.render.tileentity.RenderLaunchTable;
import com.hbm.render.tileentity.RenderLightTurret;
import com.hbm.render.tileentity.RenderMachineForceField;
import com.hbm.render.tileentity.RenderMicrowave;
import com.hbm.render.tileentity.RenderMiningDrill;
import com.hbm.render.tileentity.RenderMissileAssembly;
import com.hbm.render.tileentity.RenderMultiblock;
import com.hbm.render.tileentity.RenderNukeBoy;
import com.hbm.render.tileentity.RenderNukeCustom;
import com.hbm.render.tileentity.RenderNukeFleija;
import com.hbm.render.tileentity.RenderNukeFstbmb;
import com.hbm.render.tileentity.RenderNukeGadget;
import com.hbm.render.tileentity.RenderNukeMan;
import com.hbm.render.tileentity.RenderNukeN2;
import com.hbm.render.tileentity.RenderNukeN45;
import com.hbm.render.tileentity.RenderNukePrototype;
import com.hbm.render.tileentity.RenderNukeSolinium;
import com.hbm.render.tileentity.RenderNukeTsar;
import com.hbm.render.tileentity.RenderObjTester;
import com.hbm.render.tileentity.RenderOilDuct;
import com.hbm.render.tileentity.RenderPlasmaHeater;
import com.hbm.render.tileentity.RenderPlasmaMultiblock;
import com.hbm.render.tileentity.RenderPoleSatelliteReceiver;
import com.hbm.render.tileentity.RenderPoleTop;
import com.hbm.render.tileentity.RenderPress;
import com.hbm.render.tileentity.RenderPuF6Tank;
import com.hbm.render.tileentity.RenderPumpjack;
import com.hbm.render.tileentity.RenderPylon;
import com.hbm.render.tileentity.RenderRTG;
import com.hbm.render.tileentity.RenderRadGen;
import com.hbm.render.tileentity.RenderRadar;
import com.hbm.render.tileentity.RenderRadioRec;
import com.hbm.render.tileentity.RenderRadiobox;
import com.hbm.render.tileentity.RenderRailgun;
import com.hbm.render.tileentity.RenderRefinery;
import com.hbm.render.tileentity.RenderRocketTurret;
import com.hbm.render.tileentity.RenderSatDock;
import com.hbm.render.tileentity.RenderSelenium;
import com.hbm.render.tileentity.RenderSlidingBlastDoor;
import com.hbm.render.tileentity.RenderSmallReactor;
import com.hbm.render.tileentity.RenderSolarBoiler;
import com.hbm.render.tileentity.RenderSolarMirror;
import com.hbm.render.tileentity.RenderSoyuzLauncher;
import com.hbm.render.tileentity.RenderSoyuzMultiblock;
import com.hbm.render.tileentity.RenderSpitfireTurret;
import com.hbm.render.tileentity.RenderStructureMarker;
import com.hbm.render.tileentity.RenderTaint;
import com.hbm.render.tileentity.RenderTauTurret;
import com.hbm.render.tileentity.RenderTesla;
import com.hbm.render.tileentity.RenderTestRender;
import com.hbm.render.tileentity.RenderTurbofan;
import com.hbm.render.tileentity.RenderUF6Tank;
import com.hbm.render.tileentity.RenderVaultDoor;
import com.hbm.render.util.HmfModelLoader;
import com.hbm.sound.AudioWrapper;
import com.hbm.sound.AudioWrapperClient;
import com.hbm.tileentity.TileEntityKeypadBase;
import com.hbm.tileentity.TileEntitySlidingBlastDoorKeypad;
import com.hbm.tileentity.bomb.RenderNukeMike;
import com.hbm.tileentity.bomb.TileEntityBombMulti;
import com.hbm.tileentity.bomb.TileEntityCompactLauncher;
import com.hbm.tileentity.bomb.TileEntityCrashedBomb;
import com.hbm.tileentity.bomb.TileEntityLandmine;
import com.hbm.tileentity.bomb.TileEntityLaunchPad;
import com.hbm.tileentity.bomb.TileEntityLaunchTable;
import com.hbm.tileentity.bomb.TileEntityNukeBalefire;
import com.hbm.tileentity.bomb.TileEntityNukeBoy;
import com.hbm.tileentity.bomb.TileEntityNukeCustom;
import com.hbm.tileentity.bomb.TileEntityNukeFleija;
import com.hbm.tileentity.bomb.TileEntityNukeGadget;
import com.hbm.tileentity.bomb.TileEntityNukeMan;
import com.hbm.tileentity.bomb.TileEntityNukeMike;
import com.hbm.tileentity.bomb.TileEntityNukeN2;
import com.hbm.tileentity.bomb.TileEntityNukeN45;
import com.hbm.tileentity.bomb.TileEntityNukePrototype;
import com.hbm.tileentity.bomb.TileEntityNukeSolinium;
import com.hbm.tileentity.bomb.TileEntityNukeTsar;
import com.hbm.tileentity.bomb.TileEntityRailgun;
import com.hbm.tileentity.bomb.TileEntityTurretCIWS;
import com.hbm.tileentity.bomb.TileEntityTurretCheapo;
import com.hbm.tileentity.bomb.TileEntityTurretFlamer;
import com.hbm.tileentity.bomb.TileEntityTurretHeavy;
import com.hbm.tileentity.bomb.TileEntityTurretLight;
import com.hbm.tileentity.bomb.TileEntityTurretRocket;
import com.hbm.tileentity.bomb.TileEntityTurretSpitfire;
import com.hbm.tileentity.bomb.TileEntityTurretTau;
import com.hbm.tileentity.conductor.TileEntityCable;
import com.hbm.tileentity.conductor.TileEntityFFFluidDuct;
import com.hbm.tileentity.conductor.TileEntityFFFluidDuctMk2;
import com.hbm.tileentity.conductor.TileEntityFFFluidSuccMk2;
import com.hbm.tileentity.conductor.TileEntityFFGasDuct;
import com.hbm.tileentity.conductor.TileEntityFFOilDuct;
import com.hbm.tileentity.deco.TileEntityDecoBlock;
import com.hbm.tileentity.deco.TileEntityDecoBlockAlt;
import com.hbm.tileentity.deco.TileEntityDecoPoleSatelliteReceiver;
import com.hbm.tileentity.deco.TileEntityDecoPoleTop;
import com.hbm.tileentity.deco.TileEntityObjTester;
import com.hbm.tileentity.deco.TileEntityTestRender;
import com.hbm.tileentity.generic.TileEntityCloudResidue;
import com.hbm.tileentity.generic.TileEntityTaint;
import com.hbm.tileentity.machine.RenderBookCrafting;
import com.hbm.tileentity.machine.TileEntityAMSBase;
import com.hbm.tileentity.machine.TileEntityAMSEmitter;
import com.hbm.tileentity.machine.TileEntityAMSLimiter;
import com.hbm.tileentity.machine.TileEntityBarrel;
import com.hbm.tileentity.machine.TileEntityBlackBook;
import com.hbm.tileentity.machine.TileEntityBlastDoor;
import com.hbm.tileentity.machine.TileEntityBroadcaster;
import com.hbm.tileentity.machine.TileEntityCore;
import com.hbm.tileentity.machine.TileEntityCoreEmitter;
import com.hbm.tileentity.machine.TileEntityCoreInjector;
import com.hbm.tileentity.machine.TileEntityCoreReceiver;
import com.hbm.tileentity.machine.TileEntityCoreStabilizer;
import com.hbm.tileentity.machine.TileEntityForceField;
import com.hbm.tileentity.machine.TileEntityGeiger;
import com.hbm.tileentity.machine.TileEntityITER;
import com.hbm.tileentity.machine.TileEntityITERStruct;
import com.hbm.tileentity.machine.TileEntityMachineAssembler;
import com.hbm.tileentity.machine.TileEntityMachineCentrifuge;
import com.hbm.tileentity.machine.TileEntityMachineChemplant;
import com.hbm.tileentity.machine.TileEntityMachineCrystallizer;
import com.hbm.tileentity.machine.TileEntityMachineCyclotron;
import com.hbm.tileentity.machine.TileEntityMachineEPress;
import com.hbm.tileentity.machine.TileEntityMachineFENSU;
import com.hbm.tileentity.machine.TileEntityMachineFluidTank;
import com.hbm.tileentity.machine.TileEntityMachineGasCent;
import com.hbm.tileentity.machine.TileEntityMachineGasFlare;
import com.hbm.tileentity.machine.TileEntityMachineIGenerator;
import com.hbm.tileentity.machine.TileEntityMachineLargeTurbine;
import com.hbm.tileentity.machine.TileEntityMachineMiniRTG;
import com.hbm.tileentity.machine.TileEntityMachineMiningDrill;
import com.hbm.tileentity.machine.TileEntityMachineMiningLaser;
import com.hbm.tileentity.machine.TileEntityMachineMissileAssembly;
import com.hbm.tileentity.machine.TileEntityMachineOilWell;
import com.hbm.tileentity.machine.TileEntityMachinePlasmaHeater;
import com.hbm.tileentity.machine.TileEntityMachinePress;
import com.hbm.tileentity.machine.TileEntityMachinePuF6Tank;
import com.hbm.tileentity.machine.TileEntityMachinePumpjack;
import com.hbm.tileentity.machine.TileEntityMachineRTG;
import com.hbm.tileentity.machine.TileEntityMachineRadGen;
import com.hbm.tileentity.machine.TileEntityMachineRadar;
import com.hbm.tileentity.machine.TileEntityMachineReactor;
import com.hbm.tileentity.machine.TileEntityMachineReactorSmall;
import com.hbm.tileentity.machine.TileEntityMachineRefinery;
import com.hbm.tileentity.machine.TileEntityMachineSatDock;
import com.hbm.tileentity.machine.TileEntityMachineSeleniumEngine;
import com.hbm.tileentity.machine.TileEntityMachineTurbofan;
import com.hbm.tileentity.machine.TileEntityMachineUF6Tank;
import com.hbm.tileentity.machine.TileEntityMicrowave;
import com.hbm.tileentity.machine.TileEntityMultiblock;
import com.hbm.tileentity.machine.TileEntityPlasmaStruct;
import com.hbm.tileentity.machine.TileEntityPylonRedWire;
import com.hbm.tileentity.machine.TileEntityRadioRec;
import com.hbm.tileentity.machine.TileEntityRadiobox;
import com.hbm.tileentity.machine.TileEntitySlidingBlastDoor;
import com.hbm.tileentity.machine.TileEntitySolarBoiler;
import com.hbm.tileentity.machine.TileEntitySolarMirror;
import com.hbm.tileentity.machine.TileEntitySoyuzCapsule;
import com.hbm.tileentity.machine.TileEntitySoyuzLauncher;
import com.hbm.tileentity.machine.TileEntitySoyuzStruct;
import com.hbm.tileentity.machine.TileEntityStructureMarker;
import com.hbm.tileentity.machine.TileEntityTesla;
import com.hbm.tileentity.machine.TileEntityVaultDoor;
import com.hbm.util.BobMathUtil;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleBlockDust;
import net.minecraft.client.particle.ParticleCloud;
import net.minecraft.client.particle.ParticleFirework;
import net.minecraft.client.particle.ParticleFirework.Spark;
import net.minecraft.client.particle.ParticleFlame;
import net.minecraft.client.particle.ParticleRedstone;
import net.minecraft.client.particle.ParticleSmokeNormal;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.IRegistry;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class ClientProxy extends ServerProxy {
	
	public static KeyBinding jetpackActivate;
	public static KeyBinding jetpackHover;
	
	public static final ModelResourceLocation IRRELEVANT_MRL = new ModelResourceLocation("hbm:placeholdermodel", "inventory");
	
	//Drillgon200: This is stupid, but I'm lazy
	public static boolean renderingConstant = false;
	
	public static Field partialTicksPaused;

	public static final FloatBuffer AUX_GL_BUFFER = GLAllocation.createDirectFloatBuffer(16);
	public static final FloatBuffer AUX_GL_BUFFER2 = GLAllocation.createDirectFloatBuffer(16);
	
	//Drillgon200: Will I ever figure out how to write better code than this?
	public static final List<Runnable> deferredRenderers = new ArrayList<>();
	
	@Override
	public File getDataDir() {
		return Minecraft.getMinecraft().mcDataDir;
	}
	
	@Override
	public void registerRenderInfo()
	{
		if(!Minecraft.getMinecraft().getFramebuffer().isStencilEnabled())
			Minecraft.getMinecraft().getFramebuffer().enableStencil();
		
		MinecraftForge.EVENT_BUS.register(new ModEventHandlerClient());
		AdvancedModelLoader.registerModelHandler(new HmfModelLoader());
		
		HbmShaderManager.loadShaders();
		
		jetpackActivate = new KeyBinding("key.jetpack_activate", KeyConflictContext.IN_GAME, Keyboard.KEY_J, "key.categories.hbm");
		ClientRegistry.registerKeyBinding(jetpackActivate);
		jetpackHover = new KeyBinding("key.jetpack_hover", KeyConflictContext.IN_GAME, Keyboard.KEY_H, "key.categories.hbm");
		ClientRegistry.registerKeyBinding(jetpackHover);
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachinePress.class, new RenderPress());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineAssembler.class, new RenderAssembler());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTaint.class, new RenderTaint());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTestRender.class, new RenderTestRender());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineChemplant.class, new RenderChemplant());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCloudResidue.class, new RenderCloudResidue());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNukeMan.class, new RenderNukeMan());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNukeFleija.class, new RenderNukeFleija());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineReactorSmall.class, new RenderSmallReactor());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCable.class, new RenderCable());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFFFluidDuct.class, new RenderFluidDuct());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFFOilDuct.class, new RenderOilDuct());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFFGasDuct.class, new RenderGasDuct());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTurretCheapo.class, new RenderCheapoTurret());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTurretRocket.class, new RenderRocketTurret());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTurretLight.class, new RenderLightTurret());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTurretHeavy.class, new RenderHeavyTurret());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTurretFlamer.class, new RenderFlamerTurret());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTurretTau.class, new RenderTauTurret());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTurretSpitfire.class, new RenderSpitfireTurret());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTurretCIWS.class, new RenderCIWSTurret());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDecoBlock.class, new RenderDecoBlock());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLaunchPad.class, new RenderLaunchPadTier1());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineEPress.class, new RenderEPress());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPylonRedWire.class, new RenderPylon());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineCentrifuge.class, new RenderCentrifuge());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineGasCent.class, new RenderGasCent());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineUF6Tank.class, new RenderUF6Tank());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachinePuF6Tank.class, new RenderPuF6Tank());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRailgun.class, new RenderRailgun());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineFluidTank.class, new RenderFluidTank());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineRefinery.class, new RenderRefinery());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineCyclotron.class, new RenderCyclotron());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBroadcaster.class, new RenderBroadcaster());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGeiger.class, new RenderGeiger());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVaultDoor.class, new RenderVaultDoor());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlastDoor.class, new RenderBlastDoor());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineSeleniumEngine.class, new RenderSelenium());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineRadGen.class, new RenderRadGen());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineOilWell.class, new RenderDerrick());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachinePumpjack.class, new RenderPumpjack());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineGasFlare.class, new RenderGasFlare());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineMiningDrill.class, new RenderMiningDrill());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineTurbofan.class, new RenderTurbofan());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRadiobox.class, new RenderRadiobox());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRadioRec.class, new RenderRadioRec());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStructureMarker.class, new RenderStructureMarker());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNukeGadget.class, new RenderNukeGadget());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNukeBoy.class, new RenderNukeBoy());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNukeMike.class, new RenderNukeMike());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNukeTsar.class, new RenderNukeTsar());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNukePrototype.class, new RenderNukePrototype());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNukeSolinium.class, new RenderNukeSolinium());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNukeN2.class, new RenderNukeN2());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNukeN45.class, new RenderNukeN45());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNukeCustom.class, new RenderNukeCustom());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBombMulti.class, new RenderBombMulti());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrashedBomb.class, new RenderCrashedBomb());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLandmine.class, new RenderLandmine());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineMissileAssembly.class, new RenderMissileAssembly());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCompactLauncher.class, new RenderCompactLauncher());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMultiblock.class, new RenderMultiblock());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLaunchTable.class, new RenderLaunchTable());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySoyuzLauncher.class, new RenderSoyuzLauncher());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAMSEmitter.class, new RenderAMSEmitter());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAMSBase.class, new RenderAMSBase());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAMSLimiter.class, new RenderAMSLimiter());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineSatDock.class, new RenderSatDock());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityForceField.class, new RenderMachineForceField());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineRadar.class, new RenderRadar());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDecoPoleTop.class, new RenderPoleTop());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDecoPoleSatelliteReceiver.class, new RenderPoleSatelliteReceiver());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityObjTester.class, new RenderObjTester());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDecoBlockAlt.class, new RenderDecoBlockAlt());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFFFluidDuctMk2.class, new RenderFluidDuctMk2<TileEntityFFFluidDuctMk2>());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFFFluidSuccMk2.class, new RenderFluidDuctMk2<TileEntityFFFluidSuccMk2>());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBarrel.class, new RenderFluidBarrel());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTesla.class, new RenderTesla());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCoreEmitter.class, new RenderCoreComponent());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCoreReceiver.class, new RenderCoreComponent());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCoreInjector.class, new RenderCoreComponent());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCoreStabilizer.class, new RenderCoreComponent());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCore.class, new RenderCore());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySoyuzCapsule.class, new RenderCapsule());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySoyuzStruct.class, new RenderSoyuzMultiblock());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineMiningLaser.class, new RenderLaserMiner());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityITERStruct.class, new RenderITERMultiblock());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNukeBalefire.class, new RenderNukeFstbmb());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineCrystallizer.class, new RenderCrystallizer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMicrowave.class, new RenderMicrowave());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineRTG.class, new RenderRTG());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineMiniRTG.class, new RenderRTG());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityITER.class, new RenderITER());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineFENSU.class, new RenderFENSU());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachinePlasmaHeater.class, new RenderPlasmaHeater());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlasmaStruct.class, new RenderPlasmaMultiblock());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineLargeTurbine.class, new RenderBigTurbine());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineReactor.class, new RenderBreeder());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySlidingBlastDoor.class, new RenderSlidingBlastDoor());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityKeypadBase.class, new RenderKeypadBase());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySlidingBlastDoorKeypad.class, new RenderKeypadBase());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlackBook.class, new RenderBookCrafting());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySolarBoiler.class, new RenderSolarBoiler());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySolarMirror.class, new RenderSolarMirror());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineIGenerator.class, new RenderIGenerator());
		
		RenderingRegistry.registerEntityRenderingHandler(EntityFogFX.class, new RenderFogRenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(EntityDSmokeFX.class, new MultiCloudRendererFactory(new Item[] {ModItems.d_smoke1, ModItems.d_smoke2, ModItems.d_smoke3, ModItems.d_smoke4, ModItems.d_smoke5, ModItems.d_smoke6, ModItems.d_smoke7, ModItems.d_smoke8}));
		RenderingRegistry.registerEntityRenderingHandler(EntityOrangeFX.class, new MultiCloudRendererFactory(new Item[] {ModItems.orange1, ModItems.orange2, ModItems.orange3, ModItems.orange4, ModItems.orange5, ModItems.orange6, ModItems.orange7, ModItems.orange8}));
		RenderingRegistry.registerEntityRenderingHandler(EntityCloudFX.class, new MultiCloudRendererFactory(new Item[]{ModItems.cloud1, ModItems.cloud2, ModItems.cloud3, ModItems.cloud4, ModItems.cloud5, ModItems.cloud6, ModItems.cloud7, ModItems.cloud8}));
		RenderingRegistry.registerEntityRenderingHandler(EntityPinkCloudFX.class, new MultiCloudRendererFactory(new Item[] { ModItems.pc1, ModItems.pc2, ModItems.pc3, ModItems.pc4, ModItems.pc5, ModItems.pc6, ModItems.pc7, ModItems.pc8 }));
		RenderingRegistry.registerEntityRenderingHandler(EntityChlorineFX.class, new MultiCloudRendererFactory(new Item[] { ModItems.chlorine1, ModItems.chlorine2, ModItems.chlorine3, ModItems.chlorine4, ModItems.chlorine5, ModItems.chlorine6, ModItems.chlorine7, ModItems.chlorine8 }));
		RenderingRegistry.registerEntityRenderingHandler(EntityNukeCloudSmall.class, RenderSmallNukeMK4.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityTaintedCreeper.class, new RenderTaintedCreeperFactory());
		RenderingRegistry.registerEntityRenderingHandler(EntityNuclearCreeper.class, new RenderNuclearCreeperFactory());
		RenderingRegistry.registerEntityRenderingHandler(EntityFalloutRain.class, new RenderFalloutRainFactory());
		RenderingRegistry.registerEntityRenderingHandler(EntitySmokeFX.class, new MultiCloudRendererFactory(new Item[] {ModItems.smoke1, ModItems.smoke2, ModItems.smoke3, ModItems.smoke4, ModItems.smoke5, ModItems.smoke6, ModItems.smoke7, ModItems.smoke8}));
		RenderingRegistry.registerEntityRenderingHandler(EntityBSmokeFX.class, new MultiCloudRendererFactory(new Item[] {ModItems.b_smoke1, ModItems.b_smoke2, ModItems.b_smoke3, ModItems.b_smoke4, ModItems.b_smoke5, ModItems.b_smoke6, ModItems.b_smoke7, ModItems.b_smoke8}));
		RenderingRegistry.registerEntityRenderingHandler(EntityShrapnel.class, new ShrapnelRendererFactory());
		RenderingRegistry.registerEntityRenderingHandler(EntitySSmokeFX.class, new RenderSSmokeFactory(ModItems.nuclear_waste));
		RenderingRegistry.registerEntityRenderingHandler(EntityRubble.class, new RenderRubbleFactory());
		RenderingRegistry.registerEntityRenderingHandler(EntityBurningFOEQ.class, new RenderBurningFOEQFactory());
		RenderingRegistry.registerEntityRenderingHandler(EntityCloudFleijaRainbow.class, RenderCloudRainbow.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityExplosiveBeam.class, RenderBeam5.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityNukeCloudNoShroom.class, RenderNoCloud.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityCloudFleija.class, RenderCloudFleija.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, RenderBullet.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityGasFlameFX.class, GasFlameRenderer.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRocket.class, RenderRocket.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityFire.class, RenderFireProjectile.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityAAShell.class, RenderAAShell.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBomber.class, RenderBomber.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMissileGeneric.class, RenderMissileGeneric.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRocketHoming.class, RenderSRocket.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityTSmokeFX.class, TSmokeRenderer.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBoxcar.class, RenderBoxcar.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBombletZeta.class, RenderBombletZeta.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMissileIncendiary.class, RenderMissileIncendiary.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMissileCluster.class, RenderMissileCluster.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMissileBunkerBuster.class, RenderMissileBunkerBuster.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMissileStrong.class, RenderMissileStrong.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMissileIncendiaryStrong.class, RenderMissileIncendiaryStrong.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMissileClusterStrong.class, RenderMissileClusterStrong.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMissileBusterStrong.class, RenderMissileBusterStrong.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMissileEMPStrong.class, RenderMissileEMPStrong.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityEMP.class, RenderEmpty.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMissileBurst.class, RenderMissileBurst.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMissileInferno.class, RenderMissileInferno.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMissileRain.class, RenderMissileRain.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMissileDrill.class, RenderMissileDrill.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMissileNuclear.class, RenderMissileNuclear.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMissileMirv.class, RenderMissileMirv.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMissileEndo.class, RenderMissileEndo.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMissileExo.class, RenderMissileExo.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBombletTheta.class, RenderBombletTheta.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBombletSelena.class, RenderBombletSelena.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMissileDoomsday.class, RenderMissileDoomsday.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMissileTaint.class, RenderMissileTaint.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMissileMicro.class, RenderMissileMicro.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMissileBHole.class, RenderMissileBHole.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBlackHole.class, RenderBlackHole.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMissileSchrabidium.class, RenderMissileSchrabidium.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityEMPBlast.class, RenderEMPBlast.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMissileEMP.class, RenderMissileEMP.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMissileAntiBallistic.class, RenderMissileAB.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBooster.class, RenderBoosterMissile.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityCarrier.class, RenderCarrierMissile.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBulletBase.class, RenderBulletMk2.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityDuchessGambit.class, RenderBoat.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntitySparkBeam.class, RenderBeam4.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityModBeam.class, RenderBeam6.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityVortex.class, RenderVortex.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRagingVortex.class, RenderRagingVortex.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityNukeExplosionMK4.class, RenderNukeMK4.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMiniNuke.class, RenderMiniNuke.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMiniMIRV.class, RenderMiniMIRV.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBaleflare.class, RenderBaleflare.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRainbow.class, RenderRainbow.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPlasmaBeam.class, RenderBeam.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityLN2.class, RenderLN2.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityCombineBall.class, (RenderManager man) -> {return new RenderSnowball<EntityCombineBall>(man, ModItems.energy_ball, Minecraft.getMinecraft().getRenderItem()){
			@Override
			public void doRender(EntityCombineBall entity, double x, double y, double z, float entityYaw, float partialTicks)
		    {
		        GlStateManager.disableLighting();
		        super.doRender(entity, x, y, z, entityYaw, partialTicks);
		        GlStateManager.enableLighting();
		    }
		};});
		RenderingRegistry.registerEntityRenderingHandler(EntityDischarge.class, ElectricityRenderer.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeGeneric.class, (RenderManager man) -> {return new RenderSnowball<EntityGrenadeGeneric>(man, ModItems.grenade_generic, Minecraft.getMinecraft().getRenderItem());});
		registerGrenadeRenderer(EntityGrenadeStrong.class, ModItems.grenade_strong);
		registerGrenadeRenderer(EntityGrenadeFrag.class, ModItems.grenade_frag);
		registerGrenadeRenderer(EntityGrenadeFire.class, ModItems.grenade_fire);
		registerGrenadeRenderer(EntityGrenadeCluster.class, ModItems.grenade_cluster);
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeFlare.class, RenderFlare.FACTORY);
		registerGrenadeRenderer(EntityGrenadeElectric.class, ModItems.grenade_electric);
		registerGrenadeRenderer(EntityGrenadePoison.class, ModItems.grenade_poison);
		registerGrenadeRenderer(EntityGrenadeGas.class, ModItems.grenade_gas);
		RenderingRegistry.registerEntityRenderingHandler(EntitySchrab.class, RenderFlare.FACTORY_SCHRAB);
		registerGrenadeRenderer(EntityGrenadeSchrabidium.class, ModItems.grenade_schrabidium);
		registerGrenadeRenderer(EntityGrenadePulse.class, ModItems.grenade_pulse);
		registerGrenadeRenderer(EntityGrenadePlasma.class, ModItems.grenade_plasma);
		registerGrenadeRenderer(EntityGrenadeTau.class, ModItems.grenade_tau);
		registerGrenadeRenderer(EntityGrenadeCloud.class, ModItems.grenade_cloud);
		registerGrenadeRenderer(EntityGrenadePC.class, ModItems.grenade_pink_cloud);
		registerGrenadeRenderer(EntityGrenadeSmart.class, ModItems.grenade_smart);
		registerGrenadeRenderer(EntityGrenadeMIRV.class, ModItems.grenade_mirv);
		registerGrenadeRenderer(EntityGrenadeBreach.class, ModItems.grenade_breach);
		registerGrenadeRenderer(EntityGrenadeBurst.class, ModItems.grenade_burst);
		registerGrenadeRenderer(EntityGrenadeLemon.class, ModItems.grenade_lemon);
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeMk2.class, RenderGrenade.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeASchrab.class, RenderGrenade.FACTORY);
		registerGrenadeRenderer(EntityGrenadeZOMG.class, ModItems.grenade_zomg);
		registerGrenadeRenderer(EntityGrenadeShrapnel.class, ModItems.grenade_shrapnel);
		registerGrenadeRenderer(EntityGrenadeBlackHole.class, ModItems.grenade_black_hole);
		registerGrenadeRenderer(EntityGrenadeGascan.class, ModItems.grenade_gascan);
		registerGrenadeRenderer(EntityGrenadeNuke.class, ModItems.grenade_nuke);
		registerGrenadeRenderer(EntityGrenadeNuclear.class, ModItems.grenade_nuclear);
		registerGrenadeRenderer(EntityGrenadeIFGeneric.class, ModItems.grenade_if_generic);
		registerGrenadeRenderer(EntityGrenadeIFHE.class, ModItems.grenade_if_he);
		registerGrenadeRenderer(EntityGrenadeIFBouncy.class, ModItems.grenade_if_bouncy);
		registerGrenadeRenderer(EntityGrenadeIFSticky.class, ModItems.grenade_if_sticky);
		registerGrenadeRenderer(EntityGrenadeIFImpact.class, ModItems.grenade_if_impact);
		registerGrenadeRenderer(EntityGrenadeIFIncendiary.class, ModItems.grenade_if_incendiary);
		registerGrenadeRenderer(EntityGrenadeIFToxic.class, ModItems.grenade_if_toxic);
		registerGrenadeRenderer(EntityGrenadeIFConcussion.class, ModItems.grenade_if_concussion);
		registerGrenadeRenderer(EntityGrenadeIFBrimstone.class, ModItems.grenade_if_brimstone);
		registerGrenadeRenderer(EntityGrenadeIFMystery.class, ModItems.grenade_if_mystery);
		registerGrenadeRenderer(EntityGrenadeIFSpark.class, ModItems.grenade_if_spark);
		registerGrenadeRenderer(EntityGrenadeIFHopwire.class, ModItems.grenade_if_hopwire);
		registerGrenadeRenderer(EntityGrenadeIFNull.class, ModItems.grenade_if_null);
		RenderingRegistry.registerEntityRenderingHandler(EntityRailgunBlast.class, RenderTom.RAIL_FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBlast.class, RenderEmpty.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityNukeExplosionMK3.class, RenderEmpty.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityGasFX.class, GasRenderer.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityOilSpill.class, RenderEmpty.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityOilSpillFX.class, SpillRenderer.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityCloudSolinium.class, RenderCloudSolinium.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityNukeCloudBig.class, RenderBigNuke.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityNukeExplosionPlus.class, RenderEmpty.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityFallingNuke.class, RenderFallingNuke.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMissileCustom.class, RenderMissileCustom.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityDeathBlast.class, RenderDeathBlast.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMinerRocket.class, RenderMinerRocket.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMeteor.class, RenderMeteor.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBobmazon.class, RenderBobmazon.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityCyberCrab.class, RenderCyberCrab.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityHunterChopper.class, RenderHunterChopper.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityChopperMine.class, RenderChopperMine.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityWaterSplash.class, RenderEmpty.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMinerBeam.class, RenderBeam3.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityLaserBeam.class, RenderBeam2.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMIRV.class, RenderMirv.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBuilding.class, RenderBuilding.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityTaintCrab.class, RenderTaintCrab.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityTeslaCrab.class, RenderTeslaCrab.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityTom.class, RenderTom.TOM_FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityTomBlast.class, RenderEmpty.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntitySoyuzCapsule.class, RenderSoyuzCapsule.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntitySoyuz.class, RenderSoyuz.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityLaser.class, RenderLaser.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMovingItem.class, RenderMovingItem.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityCloudTom.class, RenderCloudTom.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMaskMan.class, RenderMaskMan.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBallsOTronSegment.class, RenderBalls.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBOTPrimeHead.class, RenderWormHead.FACTORY);
	    RenderingRegistry.registerEntityRenderingHandler(EntityBOTPrimeBody.class, RenderWormBody.FACTORY);
	    RenderingRegistry.registerEntityRenderingHandler(EntityDuck.class, RenderDuck.FACTORY);
	    RenderingRegistry.registerEntityRenderingHandler(EntityBeamVortex.class, RenderVortexBeam.FACTORY);
	    RenderingRegistry.registerEntityRenderingHandler(EntityQuackos.class, RenderQuacc.FACTORY);
	    RenderingRegistry.registerEntityRenderingHandler(EntityFBI.class, RenderFBI.FACTORY);
	    RenderingRegistry.registerEntityRenderingHandler(EntityRADBeast.class, RenderRADBeast.FACTORY);
	    RenderingRegistry.registerEntityRenderingHandler(EntityFireworks.class, RenderShrapnel.FACTORY);
		
		ModelLoader.setCustomStateMapper(ModBlocks.toxic_block, new StateMap.Builder().ignore(BlockFluidClassic.LEVEL).build());
		ModelLoader.setCustomStateMapper(ModBlocks.door_bunker, new StateMap.Builder().ignore(BlockModDoor.POWERED).build());
		ModelLoader.setCustomStateMapper(ModBlocks.door_metal, new StateMap.Builder().ignore(BlockModDoor.POWERED).build());
		ModelLoader.setCustomStateMapper(ModBlocks.door_office, new StateMap.Builder().ignore(BlockModDoor.POWERED).build());
		ModelLoader.setCustomStateMapper(ModBlocks.mud_block, new StateMap.Builder().ignore(BlockFluidClassic.LEVEL).build());
		ModelLoader.setCustomStateMapper(ModBlocks.schrabidic_block, new StateMap.Builder().ignore(BlockFluidClassic.LEVEL).build());
		ModelLoader.setCustomStateMapper(ModBlocks.seal_controller, new StateMap.Builder().ignore(BlockSeal.ACTIVATED).build());
		ModelLoader.setCustomStateMapper(ModBlocks.ntm_dirt, new StateMap.Builder().ignore(BlockDirt.SNOWY).ignore(BlockDirt.VARIANT).build());
		ModelLoader.setCustomStateMapper(ModBlocks.brick_jungle_trap, new StateMap.Builder().ignore(TrappedBrick.TYPE).build());
		//Drillgon200: This can't be efficient, but eh.
		for(Block b : ModBlocks.ALL_BLOCKS){
			if(b instanceof BlockDummyable)
				ModelLoader.setCustomStateMapper(b, new StateMap.Builder().ignore(BlockDummyable.META).build());
		}
	}
	
	private <E extends Entity> void registerGrenadeRenderer(Class<E> clazz, Item grenade) {
		RenderingRegistry.registerEntityRenderingHandler(clazz, (RenderManager man) -> {return new RenderSnowball<E>(man, grenade, Minecraft.getMinecraft().getRenderItem());});
	}
	
	@Override
	public void registerMissileItems(IRegistry<ModelResourceLocation, IBakedModel> reg) {
		MissilePart.registerAllParts();
		
		//Iterator<Map.Entry<Integer, MissilePart>> it = MissilePart.parts.entrySet().iterator();
		MissilePart.parts.values().forEach(part -> {
			part.part.setTileEntityItemStackRenderer(new ItemRenderMissilePart(part));
	        ModEventHandlerClient.swapModels(part.part, reg);
		});
	    /*while (it.hasNext()) {
	        Map.Entry<Integer, MissilePart> pair = it.next();
	        MissilePart part = (MissilePart)pair.getValue();
	        part.part.setTileEntityItemStackRenderer(new ItemRenderMissilePart(part));
	        ModEventHandlerClient.swapModels(part.part, reg);
	    }*/
		ModItems.missile_custom.setTileEntityItemStackRenderer(new ItemRenderMissile());
		ModEventHandlerClient.swapModels(ModItems.missile_custom, reg);
	}
	@Override
	public void registerTileEntitySpecialRenderer() {
		
	}
	@Override
	public void particleControl(double x, double y, double z, int type) {
		World world = Minecraft.getMinecraft().world;
		
		switch(type) {
		case 0:
			
			for(int i = 0; i < 10; i++) {
				Particle smoke = new ParticleCloud.Factory().createParticle(EnumParticleTypes.CLOUD.getParticleID(), world, x + world.rand.nextGaussian(), y + world.rand.nextGaussian(), z + world.rand.nextGaussian(), 0.0, 0.0, 0.0);
				Minecraft.getMinecraft().effectRenderer.addEffect(smoke);
			}
			break;
			
		case 1:
			Particle s = new ParticleCloud.Factory().createParticle(EnumParticleTypes.CLOUD.getParticleID(), world, x, y, z, 0.0, 0.1, 0.0);
			Minecraft.getMinecraft().effectRenderer.addEffect(s);
			
			break;
			
		case 2:
			if(GeneralConfig.instancedParticles){
				ParticleContrailInstanced contrail2 = new ParticleContrailInstanced(world, x, y, z);
				InstancedParticleRenderer.addParticle(contrail2);
			} else {
				ParticleContrail contrail = new ParticleContrail(Minecraft.getMinecraft().renderEngine, world, x, y, z);
				Minecraft.getMinecraft().effectRenderer.addEffect(contrail);
			}
			break;
		case 3:

			ParticleRadiationFog fog = new ParticleRadiationFog(world, x, y, z);
			Minecraft.getMinecraft().effectRenderer.addEffect(fog);
			break;
		}
	}
	//version 2, now with strings!
	@Override
	public void spawnParticle(double x, double y, double z, String type, float args[]) {
		World world = Minecraft.getMinecraft().world;
		TextureManager man = Minecraft.getMinecraft().renderEngine;
		
		if("launchsmoke".equals(type)) {
			ParticleSmokePlume contrail = new ParticleSmokePlume(man, world, x, y, z);
			Minecraft.getMinecraft().effectRenderer.addEffect(contrail);
		}
		if("exKerosene".equals(type)) {
			ParticleContrail contrail = new ParticleContrail(man, world, x, y, z);
			Minecraft.getMinecraft().effectRenderer.addEffect(contrail);
		}
		if("exSolid".equals(type)) {
			ParticleContrail contrail = new ParticleContrail(man, world, x, y, z, 0.3F, 0.2F, 0.05F, 1F);
			Minecraft.getMinecraft().effectRenderer.addEffect(contrail);
		}
		if("exHydrogen".equals(type)) {
			ParticleContrail contrail = new ParticleContrail(man, world, x, y, z, 0.7F, 0.7F, 0.7F, 1F);
			Minecraft.getMinecraft().effectRenderer.addEffect(contrail);
		}
		if("exBalefire".equals(type)) {
			ParticleContrail contrail = new ParticleContrail(man, world, x, y, z, 0.2F, 0.7F, 0.2F, 1F);
			Minecraft.getMinecraft().effectRenderer.addEffect(contrail);
		}
		if("bfg_fire".equals(type)){
			BlockPos pos = new BlockPos(x, y, z);
			int fireAge = (int)args[0];
			if(fireAge >= 0) {
				if(fireAge >= 1 && fireAge <= 40){
					Vec3 attractionPoint = Vec3.createVectorHelper(pos.getX() + 0.5, pos.getY() + 24, pos.getZ() + 0.5 - 60);
					for(int i = 0; i < world.rand.nextInt(6); i ++){
						float randPosX = BobMathUtil.remap(world.rand.nextFloat(), 0, 1, -10, 10);
						float randPosY = BobMathUtil.remap(world.rand.nextFloat(), 0, 1, -10, 10);
						float randPosZ = BobMathUtil.remap(world.rand.nextFloat(), 0, 1, 0, 10);
						float randMotionX = world.rand.nextFloat()*0.4F-0.2F;
						float randMotionY = world.rand.nextFloat()*0.4F-0.2F;
						float randMotionZ = world.rand.nextFloat()*0.4F-0.2F;
						Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleBFGParticle(world, pos.getX() + 0.5 + randPosX, pos.getY() + 24 + randPosY, pos.getZ() + 0.5 - 74 +  + randPosZ, randMotionX, randMotionY, randMotionZ, attractionPoint));
					}
				}
				
				if(fireAge >= 1 && fireAge <= 12 && fireAge%3 == 0){
					Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleBFGCoreLightning(world, pos.getX() + 0.5, pos.getY() + 24, pos.getZ() + 0.5 - 61));
				}
				if(fireAge >= 28 && fireAge <= 32 && fireAge%2 == 0){
					Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleBFGCoreLightning(world, pos.getX() + 0.5, pos.getY() + 24, pos.getZ() + 0.5 - 61));
				}
				if(fireAge > 32 && fireAge <= 52){
					Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleBFGCoreLightning(world, pos.getX() + 0.5, pos.getY() + 24, pos.getZ() + 0.5 - 61));
				}
				
				if(fireAge == 10){
					Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleBFGPrefire(world, pos.getX() + 0.5, pos.getY() + 24, pos.getZ() + 0.5 - 21));
				}
				
				if(fireAge == 58){
					Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleBFGBeam(world, pos.getX() + 0.5, pos.getY() + 24, pos.getZ() + 0.5 - 25));
				}
				if(fireAge >= 58 && fireAge <= 70){
					for(int i = 0; i < 20; i ++){
						float randPosX = BobMathUtil.remap(world.rand.nextFloat(), 0, 1, -5, 5);
						float randPosY = BobMathUtil.remap(world.rand.nextFloat(), 0, 1, -5, 5);
						float randPosZ = BobMathUtil.remap(world.rand.nextFloat(), 0, 1, 0, -200);
						float randMotionX = world.rand.nextFloat()*0.4F-0.2F;
						float randMotionY = world.rand.nextFloat()*0.4F-0.2F;
						float randMotionZ = world.rand.nextFloat()-5.4F-4F;
						Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleBFGParticle(world, pos.getX() + 0.5 + randPosX, pos.getY() + 24 + randPosY, pos.getZ() + 0.5 - 44 +  + randPosZ, randMotionX, randMotionY, randMotionZ, null));
					}
				}
				if(fireAge == 58 || fireAge == 64){
					Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleBFGSmoke(world, pos.getX() + 0.5, pos.getY() + 23, pos.getZ() + 0.5 - 55));
				}
				if(fireAge == 58 || fireAge == 68 || fireAge == 83){
					Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleBFGRing(world, pos.getX() + 0.5, pos.getY() + 25, pos.getZ() + 0.5 - 55));
				}
				if(fireAge == 60){
					Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleBFGShockwave(world, pos.getX() + 0.5, pos.getY() + 25, pos.getZ() + 0.5 - 55, 2, 30, 1, 0.95F));
				}
				if(fireAge == 65){
					Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleBFGShockwave(world, pos.getX() + 0.5, pos.getY() + 25, pos.getZ() + 0.5 - 65, 5, 25, 0.6F, 0.98F));
				}
				
				
			}
		}
	}
	
	//Drillgon200: Sending whole tag compounds to spawn particles can't be efficient...
	//mk3, only use this one
	@Override
	public void effectNT(NBTTagCompound data) {
		World world = Minecraft.getMinecraft().world;
		EntityPlayer player = Minecraft.getMinecraft().player;
		Random rand = world.rand;
		String type = data.getString("type");
		double x = data.getDouble("posX");
		double y = data.getDouble("posY");
		double z = data.getDouble("posZ");
		/*if("lightning".equals(type)){
			String mode = data.getString("mode");
			if("beam".equals(mode)){
				double hitX = data.getDouble("hitX");
				double hitY = data.getDouble("hitY");
				double hitZ = data.getDouble("hitZ");
				int hitType = data.getInteger("hitType");
				double length = new Vec3d(x, y, z).subtract(new Vec3d(hitX, hitY, hitZ)).lengthVector();
				//Left/right, up/down, forward/backward
				LightningGenInfo i = new LightningGenInfo();
				i.forkChance = 0;
				i.randAmount = 1F;
				i.randAmountSubdivMultiplier = 0.2F;
				i.subdivMult = 2;
				i.subdivisions = Math.max((int)(length*0.1), 1);
				i.subdivRecurse = 2;
				Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleLightningFade(player.world, x, y, z, hitX, hitY, hitZ, 0.075F, i));
				if(hitType == 0 || hitType == 1){
					Vec3d normal = new Vec3d(data.getDouble("normX"), data.getDouble("normY"), data.getDouble("normZ")).scale(0.25F);
					for(int j = 0; j < 3; j ++){
						ParticleFakeBrightness b = new ParticleFakeBrightness(player.world, hitX+normal.x+(rand.nextFloat()-0.5F)*0.1F, hitY+normal.y+(rand.nextFloat()-0.5F)*0.1F, hitZ+normal.z+(rand.nextFloat()-0.5F)*0.1F, 60, 15)
								.color(0.4F, 0.8F, 1, 2);
						b.fadeInKoeff = 10;
						Minecraft.getMinecraft().effectRenderer.addEffect(b);
					}
				}
			}
		}*/
		
		if("smoke".equals(type)) {
			
			String mode = data.getString("mode");
			int count = Math.max(1, data.getInteger("count"));
			
			if("cloud".equals(mode)) {
				
				for(int i = 0; i < count; i++) {
					if(GeneralConfig.instancedParticles){
						ParticleExSmokeInstanced fx = new ParticleExSmokeInstanced(world, x, y, z);
						double motionY = rand.nextGaussian() * (1 + (count / 100));
						double motionX = rand.nextGaussian() * (1 + (count / 150));
						double motionZ = rand.nextGaussian() * (1 + (count / 150));
						if(rand.nextBoolean()) motionY = Math.abs(motionY);
						fx.setMotion(motionX, motionY, motionZ);
						InstancedParticleRenderer.addParticle(fx);
					} else {
						ParticleExSmoke fx = new ParticleExSmoke(world, x, y, z);
						double motionY = rand.nextGaussian() * (1 + (count / 100));
						double motionX = rand.nextGaussian() * (1 + (count / 150));
						double motionZ = rand.nextGaussian() * (1 + (count / 150));
						if(rand.nextBoolean()) motionY = Math.abs(motionY);
						fx.setMotion(motionX, motionY, motionZ);
						Minecraft.getMinecraft().effectRenderer.addEffect(fx);
					}
				}
			}

			if("radial".equals(mode)) {

				for(int i = 0; i < count; i++) {
					if(GeneralConfig.instancedParticles){
						ParticleExSmokeInstanced fx = new ParticleExSmokeInstanced(world, x, y, z);
						fx.setMotion(rand.nextGaussian() * (1 + (count / 50)), rand.nextGaussian() * (1 + (count / 50)), rand.nextGaussian() * (1 + (count / 50)));
						InstancedParticleRenderer.addParticle(fx);
					} else {
						ParticleExSmoke fx = new ParticleExSmoke(world, x, y, z);
						fx.setMotion(rand.nextGaussian() * (1 + (count / 50)), rand.nextGaussian() * (1 + (count / 50)), rand.nextGaussian() * (1 + (count / 50)));
						Minecraft.getMinecraft().effectRenderer.addEffect(fx);
					}
				}
			}
			
			if("shock".equals(mode)) {
				
				double strength = data.getDouble("strength");

				Vec3 vec = Vec3.createVectorHelper(strength, 0, 0);
				vec.rotateAroundY(rand.nextInt(360));
				
				for(int i = 0; i < count; i++) {
					if(GeneralConfig.instancedParticles){
						ParticleExSmokeInstanced fx = new ParticleExSmokeInstanced(world, x, y, z);
						fx.setMotion(vec.xCoord, 0, vec.zCoord);
						InstancedParticleRenderer.addParticle(fx);
					} else {
						ParticleExSmoke fx = new ParticleExSmoke(world, x, y, z);
						fx.setMotion(vec.xCoord, 0, vec.zCoord);
						Minecraft.getMinecraft().effectRenderer.addEffect(fx);
					}
					
					vec.rotateAroundY(360 / count);
				}
			}
			
			if("shockRand".equals(mode)) {
				
				double strength = data.getDouble("strength");

				Vec3 vec = Vec3.createVectorHelper(strength, 0, 0);
				vec.rotateAroundY(rand.nextInt(360));
				double r;
				
				for(int i = 0; i < count; i++) {
					r = rand.nextDouble();
					if(GeneralConfig.instancedParticles){
						ParticleExSmokeInstanced fx = new ParticleExSmokeInstanced(world, x, y, z);
						fx.setMotion(vec.xCoord * r, 0, vec.zCoord * r);
						InstancedParticleRenderer.addParticle(fx);
					} else {
						ParticleExSmoke fx = new ParticleExSmoke(world, x, y, z);
						fx.setMotion(vec.xCoord * r, 0, vec.zCoord * r);
						Minecraft.getMinecraft().effectRenderer.addEffect(fx);
					}
					
					vec.rotateAroundY(360 / count);
				}
			}
			if("wave".equals(mode)) {

				double strength = data.getDouble("range");

				Vec3 vec = Vec3.createVectorHelper(strength, 0, 0);

				for(int i = 0; i < count; i++) {

					vec.rotateAroundY((float) Math.toRadians(rand.nextFloat() * 360F));

					if(GeneralConfig.instancedParticles){
						ParticleExSmokeInstanced fx = new ParticleExSmokeInstanced(world, x + vec.xCoord, y, z + vec.zCoord);
						fx.setMotion(0, 0, 0);
						fx.setMaxAge(50);
						InstancedParticleRenderer.addParticle(fx);
					} else {
						ParticleExSmoke fx = new ParticleExSmoke(world, x + vec.xCoord, y, z + vec.zCoord);
						fx.setMotion(0, 0, 0);
						fx.setMaxAge(50);
						Minecraft.getMinecraft().effectRenderer.addEffect(fx);
					}
					
					vec.rotateAroundY(360 / count);
				}
			}
		}
		
		if("exhaust".equals(type)) {

			String mode = data.getString("mode");
			
			if("soyuz".equals(mode)) {
				if(Vec3.createVectorHelper(player.posX - x, player.posY - y, player.posZ - z).lengthVector() > 350)
					return;
				
				int count = Math.max(1, data.getInteger("count"));
				double width = data.getDouble("width");
				
				for(int i = 0; i < count; i++) {
					if(GeneralConfig.instancedParticles){
						ParticleRocketFlameInstanced fx = new ParticleRocketFlameInstanced(world, x + rand.nextGaussian() * width, y, z + rand.nextGaussian() * width);
						fx.setMotionY(-0.75 + rand.nextDouble() * 0.5);
						InstancedParticleRenderer.addParticle(fx);
					} else {
						ParticleRocketFlame fx = new ParticleRocketFlame(world, x + rand.nextGaussian() * width, y, z + rand.nextGaussian() * width);
						fx.setMotionY(-0.75 + rand.nextDouble() * 0.5);
						Minecraft.getMinecraft().effectRenderer.addEffect(fx);
					}
					
				}
			}
			
			if("meteor".equals(mode)) {
				
				if(Vec3.createVectorHelper(player.posX - x, player.posY - y, player.posZ - z).lengthVector() > 350)
					return;
	
				int count = Math.max(1, data.getInteger("count"));
				double width = data.getDouble("width");
				
				for(int i = 0; i < count; i++) {
					if(GeneralConfig.instancedParticles){
						ParticleRocketFlameInstanced fx = new ParticleRocketFlameInstanced(world, x + rand.nextGaussian() * width, y + rand.nextGaussian() * width, z + rand.nextGaussian() * width);
						InstancedParticleRenderer.addParticle(fx);
					} else {
						ParticleRocketFlame fx = new ParticleRocketFlame(world, x + rand.nextGaussian() * width, y + rand.nextGaussian() * width, z + rand.nextGaussian() * width);
						Minecraft.getMinecraft().effectRenderer.addEffect(fx);
					}
				}
			}
		}
		
		if("fireworks".equals(type)) {
			int color = data.getInteger("color");
			char c = (char)data.getInteger("char");

			ParticleLetter fx = new ParticleLetter(world, x, y, z, color, c);
			Minecraft.getMinecraft().effectRenderer.addEffect(fx);

			for(int i = 0; i < 50; i++) {
				Spark blast = new ParticleFirework.Spark(world, x, y, z,
						0.4 * world.rand.nextGaussian(),
						0.4 * world.rand.nextGaussian(),
						0.4 * world.rand.nextGaussian(), Minecraft.getMinecraft().effectRenderer);
				blast.setColor(color);
				Minecraft.getMinecraft().effectRenderer.addEffect(blast);
			}
		}

		
		if("vanillaburst".equals(type)) {

			double motion = data.getDouble("motion");

			for(int i = 0; i < data.getInteger("count"); i++) {

				double mX = rand.nextGaussian() * motion;
				double mY = rand.nextGaussian() * motion;
				double mZ = rand.nextGaussian() * motion;
				
				Particle fx = null;

				if("flame".equals(data.getString("mode"))) {
					fx = new ParticleFlame.Factory().createParticle(-1, world, x, y, z, mX, mY, mZ);
				}
				if("cloud".equals(data.getString("mode"))) {
					fx = new ParticleCloud.Factory().createParticle(-1, world, x, y, z, mX, mY, mZ);
				}
				if("reddust".equals(data.getString("mode"))) {
					fx = new ParticleRedstone.Factory().createParticle(-1, world, x, y, z, 0.0F, 0.0F, 0.0F);
				}

				if("bluedust".equals(data.getString("mode"))) {
					fx = new ParticleRedstone.Factory().createParticle(-1, world, x, y, z, 0.01F, 0.01F, 1F);
				}

				if("greendust".equals(data.getString("mode"))) {
					fx = new ParticleRedstone.Factory().createParticle(-1, world, x, y, z, 0.01F, 0.5F, 0.1F);
				}

				if("blockdust".equals(data.getString("mode"))) {
					Block b = Block.getBlockById(data.getInteger("block"));
					fx = new ParticleBlockDust.Factory().createParticle(-1, world, x, y, z, mX, mY + 0.2, mZ, Block.getStateId(b.getDefaultState()));
					fx.setMaxAge(50+rand.nextInt(50));
				}
				
				if(fx != null)
					Minecraft.getMinecraft().effectRenderer.addEffect(fx);
			}
		}
		
		if("vanillaExt".equals(type)) {

			double mX = data.getDouble("mX");
			double mY = data.getDouble("mY");
			double mZ = data.getDouble("mZ");

			Particle fx = null;

			if("flame".equals(data.getString("mode"))) {
				fx = new ParticleFlame.Factory().createParticle(-1, world, x, y, z, mX, mY, mZ);
			}

			if("smoke".equals(data.getString("mode"))) {
				fx = new ParticleSmokeNormal.Factory().createParticle(-1, world, x, y, z, mX, mY, mZ);
			}

			if("cloud".equals(data.getString("mode"))) {
				fx = new ParticleCloud.Factory().createParticle(-1, world, x, y, z, mX, mY, mZ);
			}
			
			if("reddust".equals(data.getString("mode"))) {
				fx = new ParticleRedstone.Factory().createParticle(-1, world, x, y, z, (float)mX, (float)mY, (float)mZ);
			}

			if("bluedust".equals(data.getString("mode"))) {
				fx = new ParticleRedstone.Factory().createParticle(-1, world, x, y, z, 0.01F, 0.01F, 1F);
			}
			
			if("greendust".equals(data.getString("mode"))) {
				fx = new ParticleRedstone.Factory().createParticle(-1, world, x, y, z, 0.01F, 0.5F, 0.1F);
			}

			if(fx != null)
				Minecraft.getMinecraft().effectRenderer.addEffect(fx);
		}
		
		if("spark".equals(type)){
			String mode = data.getString("mode");
			double dirX = data.getDouble("dirX");
			double dirY = data.getDouble("dirY");
			double dirZ = data.getDouble("dirZ");
			float width = data.hasKey("width") ? data.getFloat("width") : 0.025F;
			float length = data.hasKey("length") ? data.getFloat("length") : 1.0F;
			float randLength = data.hasKey("randLength") ? data.getFloat("randLength")-length : 0;
			float gravity = data.hasKey("gravity") ? data.getFloat("gravity") : 9.81F*0.01F;
			int lifetime = data.hasKey("lifetime") ? data.getInteger("lifetime") : 100;
			int randLifeTime = data.hasKey("randLifetime") ? data.getInteger("randLifetime")-lifetime : lifetime;
			float velocityRand = data.hasKey("randomVelocity") ? data.getFloat("randomVelocity") : 1.0F;
			float r = data.hasKey("r") ? data.getFloat("r") : 1.0F;
			float g = data.hasKey("g") ? data.getFloat("g") : 1.0F;
			float b = data.hasKey("b") ? data.getFloat("b") : 1.0F;
			float a = data.hasKey("a") ? data.getFloat("a") : 1.0F;
			
			if("coneBurst".equals(mode)){
				float angle = data.hasKey("angle") ? data.getFloat("angle") : 10;
				float randAngle = data.hasKey("randAngle") ? data.getFloat("randAngle") - angle : 0;
				int count = data.hasKey("count") ? data.getInteger("count") : 1;
				for(int i = 0; i < count; i ++){
					//Gets a random vector rotated within a cone and then rotates it to the particle data's direction
					//Create a new vector and rotate it randomly about the x axis within the angle specified, then rotate that by random degrees to get the random cone vector
					Vec3 up = Vec3.createVectorHelper(0, 1, 0);
					up.rotateAroundX((float) Math.toRadians(rand.nextFloat()*(angle+rand.nextFloat()*randAngle)));
					up.rotateAroundY((float) Math.toRadians(rand.nextFloat()*360));
					//Finds the angles for the particle direction and rotate our random cone vector to it.
					Vec3 direction = Vec3.createVectorHelper(dirX, dirY, dirZ);
					Vec3 angles = BobMathUtil.getEulerAngles(direction);
					Vec3 newDirection = Vec3.createVectorHelper(up.xCoord, up.yCoord, up.zCoord);
					newDirection.rotateAroundX((float) Math.toRadians(angles.yCoord-90));
					newDirection.rotateAroundY((float) Math.toRadians(angles.xCoord));
					//Multiply it by the original vector's length to ensure it has the right magnitude
					newDirection = newDirection.mult((float) direction.lengthVector()+rand.nextFloat()*velocityRand);
					Particle fx = new ParticleSpark(world, x, y, z, length+rand.nextFloat()*randLength, width, lifetime + rand.nextInt(randLifeTime), gravity).color(r, g, b, a).motion((float)newDirection.xCoord, (float)newDirection.yCoord, (float)newDirection.zCoord);
					Minecraft.getMinecraft().effectRenderer.addEffect(fx);
				}
			}
		}
		
		if("hadron".equals(type)) {
			Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleHadron(world, x, y, z));
		}
		
		if("jetpack".equals(type)) {

			Entity ent = world.getEntityByID(data.getInteger("player"));

			if(ent instanceof EntityPlayer) {

				EntityPlayer p = (EntityPlayer)ent;

				Vec3 vec = Vec3.createVectorHelper(0, 0, -0.25);
				Vec3 offset = Vec3.createVectorHelper(0.125, 0, 0);
				float angle = (float) -Math.toRadians(p.rotationYawHead - (p.rotationYawHead - p.renderYawOffset));

				vec.rotateAroundY(angle);
				offset.rotateAroundY(angle);

				double ix = p.posX + vec.xCoord;
				double iy = p.posY + p.eyeHeight - 1;
				double iz = p.posZ + vec.zCoord;
				double ox = offset.xCoord;
				double oz = offset.zCoord;

				double moX = 0;
				double moY = 0;
				double moZ = 0;

				int mode = data.getInteger("mode");

				if(mode == 0) {
					moY -= 0.2;
				}

				if(mode == 1) {
					Vec3d look = p.getLookVec();

					moX -= look.x * 0.1D;
					moY -= look.y * 0.1D;
					moZ -= look.z * 0.1D;
				}

				Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleFlame.Factory().createParticle(-1, world, ix + ox, iy, iz + oz, p.motionX + moX * 2, p.motionY + moY * 2, p.motionZ + moZ * 2));
				Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleFlame.Factory().createParticle(-1, world, ix - ox, iy, iz - oz, p.motionX + moX * 2, p.motionY + moY * 2, p.motionZ + moZ * 2));
				Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleSmokeNormal.Factory().createParticle(-1, world, ix + ox, iy, iz + oz, p.motionX + moX * 3, p.motionY + moY * 3, p.motionZ + moZ * 3));
				Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleSmokeNormal.Factory().createParticle(-1, world, ix - ox, iy, iz - oz, p.motionX + moX * 3, p.motionY + moY * 3, p.motionZ + moZ * 3));
			}
		}

		if("muke".equals(type)) {

			ParticleMukeWave wave = new ParticleMukeWave(world, x, y, z);
			ParticleMukeFlash flash = new ParticleMukeFlash(world, x, y, z);

			Minecraft.getMinecraft().effectRenderer.addEffect(wave);
			Minecraft.getMinecraft().effectRenderer.addEffect(flash);
		}
		
		if("vanilla".equals(type)) {

			double mX = data.getDouble("mX");
			double mY = data.getDouble("mY");
			double mZ = data.getDouble("mZ");
			world.spawnParticle(EnumParticleTypes.getByName(data.getString("mode")), x, y, z, mX, mY, mZ);
		}
		
		if("anim".equals(type)) {

			EnumHand hand = EnumHand.values()[data.getInteger("hand")];
			int slot = player.inventory.currentItem;
			if(hand == EnumHand.OFF_HAND){
				slot = 9;
			}
			String name = data.getString("name");
			String mode = data.getString("mode");
			if("crucible".equals(name)){
				if("equip".equals(mode)){
					HbmAnimations.hotbar[slot] = new BlenderAnimation(player.getHeldItem(hand).getItem().getUnlocalizedName(), System.currentTimeMillis(), 1, ResourceManager.crucible_equip, new EndResult(EndType.STAY));
				}
				if("crucible".equals(mode)) {

					BusAnimation animation = new BusAnimation()
							.addBus("GUARD_ROT", new BusAnimationSequence()
									.addKeyframe(new BusAnimationKeyframe(90, 0, 1, 0))
									.addKeyframe(new BusAnimationKeyframe(90, 0, 1, 800))
									.addKeyframe(new BusAnimationKeyframe(0, 0, 1, 50)));

					HbmAnimations.hotbar[slot] = new Animation(player.getHeldItem(hand).getItem().getUnlocalizedName(), System.currentTimeMillis(), animation);
				}

				if("cSwing".equals(mode)) {

					if(HbmAnimations.getRelevantTransformation("SWING_ROT", hand)[0] == 0) {

						int offset = rand.nextInt(80)-20;

						BusAnimation animation = new BusAnimation()
								.addBus("SWING_ROT", new BusAnimationSequence()
										.addKeyframe(new BusAnimationKeyframe(90 - offset, 90 - offset, -55, 75))
										.addKeyframe(new BusAnimationKeyframe(90 + offset, 90 - offset, -45, 150))
										.addKeyframe(new BusAnimationKeyframe(0, 0, 0, 500)))
								.addBus("SWING_TRANS", new BusAnimationSequence()
										.addKeyframe(new BusAnimationKeyframe(-0, 0, 0, 75))
										.addKeyframe(new BusAnimationKeyframe(0, 0, 0, 150))
										.addKeyframe(new BusAnimationKeyframe(0, 0, 0, 500)));

						Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(HBMSoundHandler.cSwing, 0.8F + player.getRNG().nextFloat() * 0.2F));

						if(HbmAnimations.hotbar[slot] instanceof BlenderAnimation){
							HbmAnimations.hotbar[slot].animation = animation;
							HbmAnimations.hotbar[slot].startMillis = System.currentTimeMillis();
						} else {
							HbmAnimations.hotbar[slot] = new Animation(player.getHeldItem(hand).getItem().getUnlocalizedName(), System.currentTimeMillis(), animation);
						}
					}
				}
			} else if("hs_sword".equals(name)){
				if("equip".equals(mode)){
					HbmAnimations.hotbar[slot] = new BlenderAnimation(player.getHeldItem(hand).getItem().getUnlocalizedName(), System.currentTimeMillis(), 1, ResourceManager.hs_sword_equip, new EndResult(EndType.STAY));
				} else if("swing".equals(mode)){
					BusAnimation animation = new BusAnimation()
							.addBus("SWING", new BusAnimationSequence()
									.addKeyframe(new BusAnimationKeyframe(120, 0, 0, 150))
									.addKeyframe(new BusAnimationKeyframe(0, 0, 0, 500)));
					if(HbmAnimations.hotbar[slot] instanceof BlenderAnimation){
						HbmAnimations.hotbar[slot].animation = animation;
						HbmAnimations.hotbar[slot].startMillis = System.currentTimeMillis();
					} else {
						HbmAnimations.hotbar[slot] = new Animation(player.getHeldItem(hand).getItem().getUnlocalizedName(), System.currentTimeMillis(), animation);
					}
				}
			} else if("hf_sword".equals(name)){
				if("equip".equals(mode)){
					HbmAnimations.hotbar[slot] = new BlenderAnimation(player.getHeldItem(hand).getItem().getUnlocalizedName(), System.currentTimeMillis(), 1, ResourceManager.hf_sword_equip, new EndResult(EndType.STAY));
				}
			}
			
		}
	}
	
	@Override
	public boolean getIsKeyPressed(EnumKeybind key) {

		if(key == EnumKeybind.JETPACK)
			return Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown();

		return false;
	}

	@Override
	public EntityPlayer me() {
		return Minecraft.getMinecraft().player;
	}
	
	@Override
	public void setRecoil(float rec) {
		RecoilHandler.verticalVelocity = rec;
	}
	
	@Override
	public void spawnSFX(World world, double posX, double posY, double posZ, int type, Vec3 payload) {
		int pow = 250;
		float angle = 25;
		float base = 0.5F;
		for(int i = 0; i < pow; i++) {

			float momentum = base * world.rand.nextFloat();
			float sway = (pow - i) / (float)pow;
			Vec3 vec = Vec3.createVectorHelper(((Vec3)payload).xCoord, ((Vec3)payload).yCoord, ((Vec3)payload).zCoord);
			vec.rotateAroundZ((float) (angle * world.rand.nextGaussian() * sway * Math.PI / 180D));
			vec.rotateAroundY((float) (angle * world.rand.nextGaussian() * sway * Math.PI / 180D));
			
			ParticleFirework.Spark blast = new ParticleFirework.Spark(world, posX, posY, posZ, vec.xCoord * momentum, vec.yCoord * momentum, vec.zCoord * momentum, Minecraft.getMinecraft().effectRenderer);
			
			if(world.rand.nextBoolean())
				blast.setColor(0x0088EA);
			else
				blast.setColor(0x52A8E6);
			
			Minecraft.getMinecraft().effectRenderer.addEffect(blast);
		}
	}
	
	@Override
	public boolean opengl33() {
		return GLContext.getCapabilities().OpenGL33;
	}
	
	@Override
	public void preInit(FMLPreInitializationEvent evt){
		OBJLoader.INSTANCE.addDomain(RefStrings.MODID);
		
		ItemRenderLibrary.init();
		
		ModItems.redstone_sword.setTileEntityItemStackRenderer(ItemRedstoneSwordRender.INSTANCE);
		ModItems.assembly_template.setTileEntityItemStackRenderer(AssemblyTemplateRender.INSTANCE);
		ModItems.gun_b92.setTileEntityItemStackRenderer(ItemRenderGunAnim.INSTANCE);
		ModItems.fluid_tank_full.setTileEntityItemStackRenderer(FluidTankRender.INSTANCE);
		ModItems.fluid_barrel_full.setTileEntityItemStackRenderer(FluidBarrelRender.INSTANCE);
		ModItems.canister_generic.setTileEntityItemStackRenderer(FluidCanisterRender.INSTANCE);
		ModItems.chemistry_template.setTileEntityItemStackRenderer(ChemTemplateRender.INSTANCE);
		ModItems.forge_fluid_identifier.setTileEntityItemStackRenderer(FFIdentifierRender.INSTANCE);
		ModItems.gun_revolver.setTileEntityItemStackRenderer(GunRevolverRender.INSTANCE);
		ModItems.gun_revolver_nightmare.setTileEntityItemStackRenderer(new ItemRenderRevolverNightmare());
		ModItems.gun_revolver_nightmare2.setTileEntityItemStackRenderer(new ItemRenderRevolverNightmare());
		ModItems.gun_revolver_iron.setTileEntityItemStackRenderer(new ItemRenderRevolverIron());
		ModItems.gun_revolver_gold.setTileEntityItemStackRenderer(new ItemRenderRevolverGold());
		ModItems.gun_revolver_lead.setTileEntityItemStackRenderer(new ItemRenderRevolverLead());
		ModItems.gun_revolver_schrabidium.setTileEntityItemStackRenderer(new ItemRenderRevolverSchrabidium());
		ModItems.gun_revolver_cursed.setTileEntityItemStackRenderer(new ItemRenderRevolverCursed());
		ModItems.gun_revolver_pip.setTileEntityItemStackRenderer(new ItemRenderOverkill());
		ModItems.gun_revolver_nopip.setTileEntityItemStackRenderer(new ItemRenderOverkill());
		ModItems.gun_revolver_blackjack.setTileEntityItemStackRenderer(new ItemRenderOverkill());
		ModItems.gun_revolver_red.setTileEntityItemStackRenderer(new ItemRenderOverkill());
		ModItems.gun_revolver_silver.setTileEntityItemStackRenderer(new ItemRenderOverkill());
		ModItems.gun_lever_action.setTileEntityItemStackRenderer(new ItemRenderGunAnim2());
		ModItems.gun_spark.setTileEntityItemStackRenderer(new ItemRenderOverkill());
		ModItems.gun_b93.setTileEntityItemStackRenderer(new RenderGunB93());
		ModItems.gun_rpg.setTileEntityItemStackRenderer(new ItemRenderRpg());
		ModItems.gun_karl.setTileEntityItemStackRenderer(new ItemRenderRpg());
		ModItems.gun_panzerschreck.setTileEntityItemStackRenderer(new ItemRenderRpg());
		ModItems.gun_hk69.setTileEntityItemStackRenderer(new ItemRenderWeaponObj());
		ModItems.gun_deagle.setTileEntityItemStackRenderer(new ItemRenderWeaponObj());
		ModItems.gun_supershotgun.setTileEntityItemStackRenderer(new ItemRenderWeaponShotty());
		ModItems.gun_fatman.setTileEntityItemStackRenderer(new ItemRenderFatMan());
		ModItems.gun_proto.setTileEntityItemStackRenderer(new ItemRenderFatMan());
		ModItems.gun_mirv.setTileEntityItemStackRenderer(new ItemRenderMIRVLauncher());
		ModItems.gun_bf.setTileEntityItemStackRenderer(new ItemRenderBFLauncher());
		ModItems.gun_zomg.setTileEntityItemStackRenderer(new ItemRenderZOMG());
		ModItems.gun_xvl1456.setTileEntityItemStackRenderer(new ItemRenderXVL1456());
		ModItems.gun_hp.setTileEntityItemStackRenderer(new ItemRenderGunHP());
		ModItems.gun_defabricator.setTileEntityItemStackRenderer(new ItemRenderGunDefab());
		ModItems.gun_uboinik.setTileEntityItemStackRenderer(new ItemRenderUboinik());
		ModItems.gun_euthanasia.setTileEntityItemStackRenderer(new ItemRenderEuthanasia());
		ModItems.gun_stinger.setTileEntityItemStackRenderer(new ItemRenderStinger());
		ModItems.gun_skystinger.setTileEntityItemStackRenderer(new ItemRenderStinger());
		ModItems.gun_mp.setTileEntityItemStackRenderer(new ItemRenderMP());
		ModItems.gun_cryolator.setTileEntityItemStackRenderer(new ItemRenderCryolator());
		ModItems.gun_jack.setTileEntityItemStackRenderer(new ItemRenderGunJack());
		ModItems.gun_immolator.setTileEntityItemStackRenderer(new ItemRenderImmolator());
		ModItems.gun_osipr.setTileEntityItemStackRenderer(new ItemRenderOSIPR());
		ModItems.gun_emp.setTileEntityItemStackRenderer(new ItemRenderEMPRay());
		ModItems.gun_revolver_inverted.setTileEntityItemStackRenderer(new ItemRenderRevolverInverted());
		ModItems.gun_lever_action_sonata.setTileEntityItemStackRenderer(new ItemRenderGunSonata());
		ModItems.gun_bolt_action_saturnite.setTileEntityItemStackRenderer(new ItemRenderGunSaturnite());
		ModItems.gun_folly.setTileEntityItemStackRenderer(new ItemRenderFolly());
		ModItems.gun_dampfmaschine.setTileEntityItemStackRenderer(new ItemRenderBullshit());
		ModItems.gun_revolver_saturnite.setTileEntityItemStackRenderer(new ItemRenderRevolverSaturnite());
		ModItems.gun_calamity.setTileEntityItemStackRenderer(new ItemRenderCalamity());
		ModItems.gun_calamity_dual.setTileEntityItemStackRenderer(new ItemRenderCalamity());
		ModItems.gun_minigun.setTileEntityItemStackRenderer(new ItemRenderMinigun());
		ModItems.gun_avenger.setTileEntityItemStackRenderer(new ItemRenderMinigun());
		ModItems.gun_lacunae.setTileEntityItemStackRenderer(new ItemRenderMinigun());
		ModItems.gun_bolt_action.setTileEntityItemStackRenderer(new ItemRenderGunAnim2());
		ModItems.gun_bolt_action_green.setTileEntityItemStackRenderer(new ItemRenderGunAnim2());
		ModItems.gun_lever_action_dark.setTileEntityItemStackRenderer(new ItemRenderGunAnim2());
		ModItems.gun_uzi.setTileEntityItemStackRenderer(new ItemRenderUzi());
		ModItems.gun_uzi_silencer.setTileEntityItemStackRenderer(new ItemRenderUzi());
		ModItems.gun_uzi_saturnite.setTileEntityItemStackRenderer(new ItemRenderUzi());
		ModItems.gun_uzi_saturnite_silencer.setTileEntityItemStackRenderer(new ItemRenderUzi());
		ModItems.gun_mp40.setTileEntityItemStackRenderer(new ItemRenderMP40());
		ModItems.cell.setTileEntityItemStackRenderer(new ItemRenderCell());
		ModItems.gas_canister.setTileEntityItemStackRenderer(new ItemRenderGasCanister());
		ModItems.multitool_dig.setTileEntityItemStackRenderer(new ItemRenderMultitool());
		ModItems.multitool_silk.setTileEntityItemStackRenderer(new ItemRenderMultitool());
		ModItems.multitool_ext.setTileEntityItemStackRenderer(new ItemRenderMultitool());
		ModItems.multitool_miner.setTileEntityItemStackRenderer(new ItemRenderMultitool());
		ModItems.multitool_hit.setTileEntityItemStackRenderer(new ItemRenderMultitool());
		ModItems.multitool_beam.setTileEntityItemStackRenderer(new ItemRenderMultitool());
		ModItems.multitool_sky.setTileEntityItemStackRenderer(new ItemRenderMultitool());
		ModItems.multitool_mega.setTileEntityItemStackRenderer(new ItemRenderMultitool());
		ModItems.multitool_joule.setTileEntityItemStackRenderer(new ItemRenderMultitool());
		ModItems.multitool_decon.setTileEntityItemStackRenderer(new ItemRenderMultitool());
		ModItems.big_sword.setTileEntityItemStackRenderer(new ItemRenderBigSword());
		ModItems.shimmer_sledge.setTileEntityItemStackRenderer(new ItemRenderShim());
		ModItems.shimmer_axe.setTileEntityItemStackRenderer(new ItemRenderShim());
		ModItems.ff_fluid_duct.setTileEntityItemStackRenderer(new ItemRenderFFFluidDuct());
		ModItems.fluid_icon.setTileEntityItemStackRenderer(new ItemRenderFluidIcon());
		ModItems.gun_brimstone.setTileEntityItemStackRenderer(new ItemRenderObj());
		ModItems.stopsign.setTileEntityItemStackRenderer(new ItemRenderShim());
		ModItems.sopsign.setTileEntityItemStackRenderer(new ItemRenderShim());
		ModItems.gun_ks23.setTileEntityItemStackRenderer(new ItemRenderWeaponObj());
		ModItems.gun_flamer.setTileEntityItemStackRenderer(new ItemRenderWeaponObj());
		ModItems.gun_flechette.setTileEntityItemStackRenderer(new ItemRenderWeaponObj());
		ModItems.gun_quadro.setTileEntityItemStackRenderer(new ItemRenderWeaponQuadro());
		ModItems.gun_sauer.setTileEntityItemStackRenderer(new ItemRenderWeaponSauer());
		ModItems.chernobylsign.setTileEntityItemStackRenderer(new ItemRenderShim());
		Item.getItemFromBlock(ModBlocks.radiorec).setTileEntityItemStackRenderer(new ItemRendererMachine(1D));
		ModItems.gun_vortex.setTileEntityItemStackRenderer(new ItemRenderWeaponVortex());
		ModItems.gun_thompson.setTileEntityItemStackRenderer(new ItemRenderWeaponThompson());
		ModItems.wood_gavel.setTileEntityItemStackRenderer(new ItemRenderGavel());
		ModItems.lead_gavel.setTileEntityItemStackRenderer(new ItemRenderGavel());
		ModItems.diamond_gavel.setTileEntityItemStackRenderer(new ItemRenderGavel());
		ModItems.mese_gavel.setTileEntityItemStackRenderer(new ItemRenderGavel());
		ModItems.gun_bolter.setTileEntityItemStackRenderer(new ItemRenderWeaponBolter());
		ModItems.ingot_meteorite.setTileEntityItemStackRenderer(new ItemRendererHot());
		ModItems.ingot_meteorite_forged.setTileEntityItemStackRenderer(new ItemRendererHot());
		ModItems.blade_meteorite.setTileEntityItemStackRenderer(new ItemRendererHot());
		ModItems.crucible.setTileEntityItemStackRenderer(new ItemRenderCrucible());
		ModItems.hs_sword.setTileEntityItemStackRenderer(new ItemRenderHSSword());
		ModItems.hf_sword.setTileEntityItemStackRenderer(new ItemRenderHFSword());
		ModItems.cc_plasma_gun.setTileEntityItemStackRenderer(new ItemRenderCCPlasmaCannon());
		
		ModItems.meteorite_sword_seared.setTileEntityItemStackRenderer(new ItemRendererMeteorSword(1.0F, 0.5F, 0.0F));
		ModItems.meteorite_sword_reforged.setTileEntityItemStackRenderer(new ItemRendererMeteorSword(0.5F, 1.0F, 1.0F));
		ModItems.meteorite_sword_hardened.setTileEntityItemStackRenderer(new ItemRendererMeteorSword(0.25F, 0.25F, 0.25F));
		ModItems.meteorite_sword_alloyed.setTileEntityItemStackRenderer(new ItemRendererMeteorSword(0.0F, 0.5F, 1.0F));
		ModItems.meteorite_sword_machined.setTileEntityItemStackRenderer(new ItemRendererMeteorSword(1.0F, 1.0F, 0.0F));
		ModItems.meteorite_sword_treated.setTileEntityItemStackRenderer(new ItemRendererMeteorSword(0.5F, 1.0F, 0.5F));
		ModItems.meteorite_sword_etched.setTileEntityItemStackRenderer(new ItemRendererMeteorSword(1.0F, 1.0F, 0.5F));
		ModItems.meteorite_sword_bred.setTileEntityItemStackRenderer(new ItemRendererMeteorSword(0.5F, 0.5F, 0.0F));
		ModItems.meteorite_sword_irradiated.setTileEntityItemStackRenderer(new ItemRendererMeteorSword(0.75F, 1.0F, 0.0F));
		ModItems.meteorite_sword_fused.setTileEntityItemStackRenderer(new ItemRendererMeteorSword(1.0F, 0.0F, 0.5F));
		ModItems.meteorite_sword_baleful.setTileEntityItemStackRenderer(new ItemRendererMeteorSword(0.0F, 1.0F, 0.0F));
		
		for(Entry<Item, ItemRenderBase> entry : ItemRenderLibrary.renderers.entrySet()){
			entry.getKey().setTileEntityItemStackRenderer(entry.getValue());
		}
	}
	
	@Override
	public AudioWrapper getLoopedSound(SoundEvent sound, SoundCategory cat, float x, float y, float z, float volume, float pitch) {
		AudioWrapperClient audio = new AudioWrapperClient(sound, cat);
		audio.updatePosition(x, y, z);
		return audio;
	}
	
	public static int boxcarCalllist;
	
	@Override
	public void postInit(FMLPostInitializationEvent e) {
		
		boxcarCalllist = GL11.glGenLists(1);
		GL11.glNewList(boxcarCalllist, GL11.GL_COMPILE);
		ResourceManager.boxcar.renderAll();
		GL11.glEndList();
		ResourceManager.loadAnimatedModels();
		Minecraft.getMinecraft().getRenderManager().getSkinMap().forEach((p, r) -> {
			r.addLayer(new JetpackHandler.JetpackLayer());
		});
		ParticleRenderLayer.register();
		BobmazonOfferFactory.reset();
		BobmazonOfferFactory.init();
	}
	
	@Override
	public void playSound(String sound, Object data) {

	}
	
	@Override
	public void displayTooltip(String msg) {
		Minecraft.getMinecraft().ingameGUI.setOverlayMessage(msg, false);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public float partialTicks() {
		try {
			if(partialTicksPaused == null){
				partialTicksPaused = ReflectionHelper.findField(Minecraft.class, "renderPartialTicksPaused", "field_193996_ah");
			}
			boolean paused = Minecraft.getMinecraft().isGamePaused();
			return paused ? partialTicksPaused.getFloat(Minecraft.getMinecraft()) : Minecraft.getMinecraft().getRenderPartialTicks();
		} catch(Exception x){
			x.printStackTrace();
		}
		return Minecraft.getMinecraft().getRenderPartialTicks();
	}
	
}