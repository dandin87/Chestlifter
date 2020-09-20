package io.github.haykam821.chestlifter.component;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.math.BlockPos;
import org.apache.commons.lang3.SerializationUtils;

public class LiftedBlock {
	private static SerializationUtils BlockState;
	private static Object BlockEntity;
	private final BlockState blockState;
	private final BlockEntity blockEntity;
	//private Object BlockState;

	public LiftedBlock(BlockState blockState, BlockEntity blockEntity) {
		this.blockState = blockState;
		this.blockEntity = blockEntity;
	}

	public BlockState getBlockState() {
		return this.blockState;
	}

	public BlockEntity getBlockEntity() {
		return this.blockEntity;
	}

	public CompoundTag getBlockEntityTagAtPosition(BlockPos pos) {
		CompoundTag blockEntityTag = this.blockEntity.toTag(new CompoundTag());

		blockEntityTag.putInt("x", pos.getX());
		blockEntityTag.putInt("y", pos.getY());
		blockEntityTag.putInt("z", pos.getZ());

		return blockEntityTag;
	}

	public CompoundTag toTag(CompoundTag tag) {
		Tag blockState;
		blockState = tag.put("BlockState", (Tag) BlockState);

		CompoundTag blockEntityTag = new CompoundTag();
		tag.put("BlockEntity", this.blockEntity.toTag(blockEntityTag));

		return tag;
	}

	public static LiftedBlock createFromTag(CompoundTag tag) {
		if (!tag.contains("BlockState", 10) || !tag.contains("BlockEntity", 10))
			return null;

		CompoundTag blockStateTag = tag.getCompound("BlockState");
		Tag blockState = (Tag) BlockState;
		if (blockState == null) return null;

		CompoundTag blockEntityTag = tag.getCompound("BlockEntity");
		BlockEntity blockEntity = (net.minecraft.block.entity.BlockEntity) BlockEntity;
		if (blockEntity == null) return null;
		
		return new LiftedBlock((net.minecraft.block.BlockState) blockState, blockEntity);
	}
}