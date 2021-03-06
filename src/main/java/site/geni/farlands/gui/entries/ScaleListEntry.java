package site.geni.farlands.gui.entries;

import me.shedaniel.clothconfig2.gui.entries.DoubleListEntry;
import net.minecraft.text.Text;
import site.geni.farlands.FarLands;
import site.geni.farlands.config.Config;
import site.geni.farlands.gui.Categories;
import site.geni.farlands.util.Location;
import site.geni.farlands.util.LocationHelper;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ScaleListEntry extends DoubleListEntry {
    @Deprecated
    public ScaleListEntry(Text fieldName, Double value, Text resetButtonKey, Supplier<Double> defaultValue, Consumer<Double> saveConsumer) {
        super(fieldName, value, resetButtonKey, defaultValue, saveConsumer);
    }

    /**
     * Updates the estimates in the "World" category
     *
     * @param config {@link Config} to use
     */
    private static void updateOptions(Config config) {
        LocationHelper.updateAll(config);

        if (Categories.World.FAR_LANDS_ESTIMATE != null &&
                Categories.World.FARTHER_LANDS_ESTIMATE != null &&
                Categories.World.FARTHERER_LANDS_ESTIMATE != null &&
                Categories.World.FARTHEST_LANDS_ESTIMATE != null
        ) {
            Categories.World.FAR_LANDS_ESTIMATE.update(Location.FAR_LANDS.getText());
            Categories.World.FARTHER_LANDS_ESTIMATE.update(Location.FARTHER_LANDS.getText());
            Categories.World.FARTHERER_LANDS_ESTIMATE.update(Location.FARTHERER_LANDS.getText());
            Categories.World.FARTHEST_LANDS_ESTIMATE.update(Location.FARTHEST_LANDS.getText());
        }
    }

    @Override
    public boolean keyPressed(int charCode, int int_1, int int_2) {
        return super.keyPressed(charCode, int_1, int_2) && updateScales();
    }

    @Override
    public boolean charTyped(char character, int charcode) {
        return super.charTyped(character, charcode) && updateScales();
    }

    private boolean updateScales() {
        try {
            final double scale = this.getValue();

            final Config config = FarLands.getConfig();
            String fieldName = this.getFieldName().asString();
            if ("config.farlands.coordinateScale".equals(fieldName)) {
                config.coordinateScale.setValue(scale);
            } else if ("config.farlands.coordinateScaleMultiplier".equals(fieldName)) {
                config.coordinateScaleMultiplier.setValue(scale);
            }

            ScaleListEntry.updateOptions(config);

            return true;
        } catch (ArithmeticException | NumberFormatException ignored) {

        }

        return false;
    }
}
