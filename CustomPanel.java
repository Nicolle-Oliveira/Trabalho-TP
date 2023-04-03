import java.awt.*;
import javax.swing.*;

public class CustomPanel extends JPanel{
    public CustomPanel()
    {
        //Fonte dados iniciais
        setFont(new Font("Alkatra", Font.BOLD, 15));
        //Borda e Background
        setBackground(new Color(193, 197, 243));
        setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, new Color(112,128,144)));

        setAlignmentX(LEFT_ALIGNMENT);
    }
}
