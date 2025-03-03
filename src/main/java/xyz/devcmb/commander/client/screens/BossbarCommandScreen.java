package xyz.devcmb.commander.client.screens;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class BossbarCommandScreen extends Screen {
    private static final Identifier TEXTURE = Identifier.of("commander", "textures/gui/cc/bossbar.png");
    private TextFieldWidget textField;
    private TextFieldWidget idField;
    private TextFieldWidget fontField;
    private TextFieldWidget colorField;
    private ButtonWidget sendButton;

    public BossbarCommandScreen() {
        super(Text.of("Bossbar"));
    }

    @Override
    protected void init() {
        super.init();

        int backgroundWidth = 256;
        int backgroundHeight = 166;

        int textFieldWidth = 242;
        int textFieldHeight = 30;

        int centerX = (width - backgroundWidth) / 2;
        int centerY = (height - backgroundHeight) / 2;

        this.textField = new TextFieldWidget(this.textRenderer, centerX + 7, centerY + 19, textFieldWidth, textFieldHeight, Text.of(""));
        this.textField.setMaxLength(1000);
        this.textField.setEditableColor(0xFFFFFF);
        this.addSelectableChild(this.textField);

        this.idField = new TextFieldWidget(this.textRenderer, centerX + 7, centerY + 62, textFieldWidth, 16, Text.of(""));
        this.idField.setMaxLength(300);
        this.idField.setEditableColor(0xFFFFFF);
        this.addSelectableChild(this.idField);

        this.fontField = new TextFieldWidget(this.textRenderer, centerX + 7, centerY + 93, textFieldWidth, 16, Text.of(""));
        this.fontField.setMaxLength(300);
        this.fontField.setEditableColor(0xFFFFFF);
        this.addSelectableChild(this.fontField);

        this.colorField = new TextFieldWidget(this.textRenderer, centerX + 7, centerY + 123, textFieldWidth, 16, Text.of(""));
        this.colorField.setMaxLength(300);
        this.colorField.setEditableColor(0xFFFFFF);
        this.addSelectableChild(this.colorField);

        this.sendButton = ButtonWidget.builder(Text.of("Send"), button -> {
                    assert this.client != null;
                    assert this.client.player != null;

                    String font = this.fontField.getText();
                    if(font.isEmpty()) font = "minecraft:default";
                    String color = this.colorField.getText();
                    if(color.isEmpty()) color = "white";

                    this.client.player.networkHandler.sendCommand(
                            "bossbar add " + this.idField.getText() + " {\"text\":\"" + this.textField.getText() + "\",\"font:\":\"" + font + "\"}"
                    );
                    this.client.player.networkHandler.sendCommand(
                            "bossbar set " + this.idField.getText() + " players @s"
                    );
                    this.client.player.networkHandler.sendCommand(
                            "bossbar set " + this.idField.getText() + " color " + color
                    );
                    this.client.player.networkHandler.sendCommand(
                            "bossbar set " + this.idField.getText() + " progress 100"
                    );
                }).dimensions(centerX + 6, centerY + 143, textFieldWidth, 16)
                .build();
        this.addDrawableChild(this.sendButton);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(0, 0, this.width, this.height, 0x88000000);

        int backgroundWidth = 256;
        int backgroundHeight = 166;

        int centerX = (width - backgroundWidth) / 2;
        int centerY = (height - backgroundHeight) / 2;
        context.drawTexture(TEXTURE, centerX, centerY, 0.0F, 0.0F, backgroundWidth, backgroundHeight, 256, 256);

        this.textField.render(context, mouseX, mouseY, delta);
        this.idField.render(context, mouseX, mouseY, delta);
        this.fontField.render(context, mouseX, mouseY, delta);
        this.colorField.render(context, mouseX, mouseY, delta);
        this.sendButton.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
