package com.jocosero.odd_water_mobs.entity.ai;

import com.jocosero.odd_water_mobs.entity.SeafloorAnimal;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Random;

public class SeafloorWander extends RandomStrollGoal {
    private int waterChance = 0;
    private int landChance = 0;
    private int range = 5;

    public SeafloorWander(PathfinderMob creature, double speed, int waterChance, int landChance) {
        super(creature, speed, waterChance);
        this.waterChance = waterChance;
        this.landChance = landChance;
    }

    public SeafloorWander(PathfinderMob creature, double speed, int waterChance, int landChance, int range) {
        super(creature, speed, waterChance);
        this.waterChance = waterChance;
        this.landChance = landChance;
        this.range = range;
    }

    public boolean canUse() {
        if (mob instanceof SeafloorAnimal && ((SeafloorAnimal) mob).shouldStopMoving()) {
            return false;
        }
        interval = mob.isInWater() ? waterChance : landChance;
        return super.canUse();
    }

    public boolean canContinueToUse() {
        if (mob instanceof SeafloorAnimal && ((SeafloorAnimal) mob).shouldStopMoving())
            return false;

        return super.canContinueToUse();
    }

    @Nullable
    protected Vec3 getPosition() {
        if (this.mob.isInWater()) {
            BlockPos blockpos = null;
            Random random = new Random();
            for (int i = 0; i < 15; i++) {
                BlockPos blockPos = this.mob.blockPosition().offset(random.nextInt(range) - range / 2, 3, random.nextInt(range) - range / 2);
                while ((this.mob.level.isEmptyBlock(blockPos) || this.mob.level.getFluidState(blockPos).is(FluidTags.WATER)) && blockPos.getY() > 1) {
                    blockPos = blockPos.below();
                }
                if (isBottomOfSeafloor(this.mob.level, blockPos.above())) {
                    blockpos = blockPos;
                }
            }

            return blockpos != null ? new Vec3(blockpos.getX() + 0.5F, blockpos.getY() + 0.5F, blockpos.getZ() + 0.5F) : null;
        } else {
            return super.getPosition();

        }
    }

    private boolean isBottomOfSeafloor(LevelAccessor world, BlockPos pos) {
        return world.getFluidState(pos).is(FluidTags.WATER) && world.getFluidState(pos.below()).isEmpty() && world.getBlockState(pos.below()).canOcclude();
    }
}
