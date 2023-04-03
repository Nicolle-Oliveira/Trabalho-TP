import java.awt.*;
import javax.swing.*;

public class CustomLabel extends JLabel
{
    public CustomLabel()
    {
        setFont(new Font("Alkatra", Font.BOLD, 15));
    }
    public CustomLabel(String texto)
    {
        // Formação
        setFont(new Font("Alkatra", Font.BOLD, 15));
        setAlignmentX(Component.LEFT_ALIGNMENT);

        // Saída
        setText(texto);
    }
}
