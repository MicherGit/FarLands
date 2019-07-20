package site.geni.FarLands.util;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.impl.builders.*;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.util.Formatting;
import site.geni.FarLands.FarLands;
import site.geni.FarLands.config.Config;
import site.geni.FarLands.gui.entries.EstimateListEntry;
import site.geni.FarLands.gui.entries.builders.EstimateFieldBuilder;
import site.geni.FarLands.gui.entries.builders.OutsideWorldFieldBuilder;
import site.geni.FarLands.gui.entries.builders.ScaleFieldBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Categories {
	public static class General {
		/**
		 * Creates all options of the "General" category
		 *
		 * @param general The "General" category's {@link ConfigCategory}
		 * @author geni
		 */
		public static void createCategory(ConfigCategory general) {
			// Adds the option for enabling the Far Lands
			general.addEntry(
				new BooleanToggleBuilder(
					"text.cloth.reset_value",
					"config.farlands.farLandsEnabled",
					FarLands.getConfig().farLandsEnabled
				).setDefaultValue(new Config().farLandsEnabled)
					.setSaveConsumer(bool -> FarLands.getConfig().farLandsEnabled = bool)
					.build()
			);

			// Adds the option for killing entities in the Far Lands
			general.addEntry(
				new BooleanToggleBuilder(
					"text.cloth.reset_value",
					"config.farlands.killEntities",
					FarLands.getConfig().killFallingBlockEntitiesInFarLands
				).setDefaultValue(new Config().killFallingBlockEntitiesInFarLands)
					.setSaveConsumer(bool -> FarLands.getConfig().killFallingBlockEntitiesInFarLands = bool)
					.build()
			);
		}
	}


	public static class World {
		public static EstimateListEntry FAR_LANDS_ESTIMATE;
		public static EstimateListEntry FARTHER_LANDS_ESTIMATE;
		public static EstimateListEntry FARTHERER_LANDS_ESTIMATE;
		public static EstimateListEntry FARTHEST_LANDS_ESTIMATE;

		/**
		 * Creates all options of the "World" category
		 *
		 * @param world The "World" category's {@link ConfigCategory}
		 * @author geni
		 */
		public static void createCategory(ConfigCategory world) {
			// Adds the warning in the "World" category
			world.addEntry(
				new TextDescriptionBuilder(
					"",
					"",
					I18n.translate("config.farlands.category.world.warning")
				).setColor(16733525)
					.build()
			);

			// Adds the option for setting the coordinate scale
			world.addEntry(
				new ScaleFieldBuilder(
					"text.cloth.reset_value",
					"config.farlands.coordinateScale",
					FarLands.getConfig().coordinateScale,
					world
				).setDefaultValue(new Config().coordinateScale)
					.setSaveConsumer(scale -> FarLands.getConfig().coordinateScale = scale)
					.build()
			);

			// Adds the option for setting the coordinate scale multiplier
			world.addEntry(
				new ScaleFieldBuilder(
					"text.cloth.reset_value",
					"config.farlands.coordinateScaleMultiplier",
					FarLands.getConfig().coordinateScaleMultiplier,
					world
				).setDefaultValue(new Config().coordinateScaleMultiplier)
					.setSaveConsumer(scale -> FarLands.getConfig().coordinateScaleMultiplier = scale)
					.build()
			);

			// Adds the option for setting the height scale
			world.addEntry(
				new DoubleFieldBuilder(
					"text.cloth.reset_value",
					"config.farlands.heightScale",
					FarLands.getConfig().heightScale
				).setDefaultValue(new Config().heightScale)
					.setSaveConsumer(scale -> FarLands.getConfig().heightScale = scale)
					.build()
			);

			// Adds the option for setting the height scale multiplier
			world.addEntry(
				new DoubleFieldBuilder(
					"text.cloth.reset_value",
					"config.farlands.heightScaleMultiplier",
					FarLands.getConfig().heightScaleMultiplier
				).setDefaultValue(new Config().heightScaleMultiplier)
					.setSaveConsumer(scale -> FarLands.getConfig().heightScaleMultiplier = scale)
					.build()
			);

			World.createEstimatesSubCategory(world);
		}

		/**
		 * @param world The "World" category's {@link ConfigCategory}
		 * @author geni
		 */
		private static void createEstimatesSubCategory(ConfigCategory world) {
			// This sub-category's entries
			List<AbstractConfigListEntry> entries = Arrays.asList(
				// Adds the estimate for the Far Lands' location
				FAR_LANDS_ESTIMATE = new EstimateFieldBuilder(
					"config.farlands.estimatedPosition",
					Location.FAR_LANDS.getText()
				).build(),

				// Adds the estimate for the Farther Lands' location
				FARTHER_LANDS_ESTIMATE = new EstimateFieldBuilder(
					"config.farlands.estimatedFartherPosition",
					Location.FARTHER_LANDS.getText()
				).build(),

				// Adds the estimate for the Fartherer Lands' location
				FARTHERER_LANDS_ESTIMATE = new EstimateFieldBuilder(
					"config.farlands.estimatedFarthererPosition",
					Location.FARTHERER_LANDS.getText()
				).build(),

				// Adds the estimate for the Farthest Lands' location
				FARTHEST_LANDS_ESTIMATE = new EstimateFieldBuilder(
					"config.farlands.estimatedFarthestPosition",
					Location.FARTHEST_LANDS.getText()
				).build()
			);

			// Adds the "Estimates" sub-category to the "World" category
			final SubCategoryBuilder subCategoryBuilder = new SubCategoryBuilder(
				"text.cloth-config.reset_value",
				"config.farlands.category.fixes.subcategory.estimates"
			);
			subCategoryBuilder.addAll(entries);
			subCategoryBuilder.setExpended(true);
			world.addEntry(subCategoryBuilder.build());
		}
	}


	public static class Fixes {
		// All particles fixed by the "Fix Particles/Entities" option, ready for usage in its tooltip
		private static final String[] PARTICLES = new String[]{
			"water", "lava", "tnt", "end_portal", "falling_block", "mycelium", "leaves", "repeater", "nether_portal"
		};

		// All entities fixed by the "Fix Particles/Entities" option, ready for usage in its tooltip
		private static final String[] ENTITIES = new String[]{
			"tnt", "enchanting_table"
		};


		/**
		 * Creates all options and sub-categories of the "Fixes" category
		 *
		 * @param fixes The "Fixes" category's {@link ConfigCategory}
		 * @author geni
		 */
		public static void createCategory(ConfigCategory fixes) {
			// Adds the option for fixing ore generation
			fixes.addEntry(
				new BooleanToggleBuilder(
					"text.cloth.reset_value",
					"config.farlands.fixOreGeneration",
					FarLands.getConfig().fixOreGeneration
				).setDefaultValue(new Config().fixOreGeneration)
					.setSaveConsumer(bool -> FarLands.getConfig().fixOreGeneration = bool)
					.setTooltip(I18n.translate("config.farlands.fixOreGeneration.tooltip"))
					.build()
			);

			// Adds the option for fixing particles/entities
			fixes.addEntry(
				new BooleanToggleBuilder(
					"text.cloth.reset_value",
					"config.farlands.fixParticlesEntities",
					FarLands.getConfig().fixParticlesEntities
				).setDefaultValue(new Config().fixParticlesEntities)
					.setSaveConsumer(bool -> FarLands.getConfig().fixParticlesEntities = bool)
					.setTooltip(Fixes.createParticlesTooltip())
					.build()
			);

			Fixes.createExperimentalSubCategory(fixes);
		}

		/**
		 * Creates the "Fixes" category's "Fix Particles/Entities" option's tooltip
		 *
		 * @return The tooltip to be used by "Fix Particles/Entities"
		 * @author geni
		 */
		private static String[] createParticlesTooltip() {
			// "Fix particles/entities" option's tooltip
			final List<String> particleTooltip = new ArrayList<>();

			// Add particles to tooltip
			particleTooltip.add(I18n.translate("config.farlands.fixParticlesEntities.tooltip.description"));
			for (final String particle : PARTICLES) {
				particleTooltip.add(Formatting.GREEN + I18n.translate("config.farlands.fixParticlesEntities.tooltip.description." + particle));
			}

			// Add entities to tooltip
			particleTooltip.add(I18n.translate("config.farlands.fixParticlesEntities.tooltip.description.entities"));
			for (final String entity : ENTITIES) {
				particleTooltip.add(Formatting.GREEN + I18n.translate("config.farlands.fixParticlesEntities.tooltip.description.entities." + entity));
			}

			return particleTooltip.toArray(new String[0]);
		}

		/**
		 * Creates all options of the "Fixes" category's "Experimental" sub-category and adds it
		 *
		 * @param fixes The "Fixes" {@link ConfigCategory}
		 * @author geni
		 */
		private static void createExperimentalSubCategory(ConfigCategory fixes) {
			// "Fixes" category's "Experimental" sub-category's entries
			List<AbstractConfigListEntry> experimentalEntries = Arrays.asList(
				// Warning message (16733525 is ChatFormat.RED's color)
				new TextDescriptionBuilder("",
					"",
					I18n.translate("config.farlands.category.fixes.subcategory.experimental.warning")
				).setColor(16733525)
					.build(),

				// Lighting
				new OutsideWorldFieldBuilder(
					"text.cloth.reset_value",
					"config.farlands.fixLighting",
					FarLands.getConfig().fixLighting
				).setTooltip(
					I18n.translate("config.farlands.fixLighting.tooltip.description"),
					Formatting.RED + I18n.translate("config.farlands.fixLighting.tooltip.warning"),
					Formatting.RED + I18n.translate("config.farlands.fixLighting.tooltip.world")
				)
					.setDefaultValue(new Config().fixLighting)
					.setSaveConsumer(bool -> FarLands.getConfig().fixLighting = bool)
					.build(),

				// Mob spawning
				new BooleanToggleBuilder(
					"text.cloth.reset_value",
					"config.farlands.fixMobSpawning",
					FarLands.getConfig().fixMobSpawning
				).setTooltip(I18n.translate("config.farlands.fixMobSpawning.tooltip"))
					.setSaveConsumer(bool -> FarLands.getConfig().fixMobSpawning = bool)
					.setDefaultValue(new Config().fixMobSpawning)
					.build()
			);

			// Adds the "Experimental" sub-category to the "Fixes" category
			final SubCategoryBuilder subCategoryBuilder = new SubCategoryBuilder(
				"text.cloth-config.reset_value",
				"config.farlands.category.fixes.subcategory.experimental"
			);
			subCategoryBuilder.addAll(experimentalEntries);
			fixes.addEntry(subCategoryBuilder.build());
		}
	}
}
