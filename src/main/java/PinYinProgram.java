import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import net.miginfocom.swing.MigLayout;

public class PinYinProgram implements ActionListener {

	private JFrame frame = null;

	private JTextComponent tfInput, tfResult = null;

	private AbstractButton btnExecute, btnCopy = null;

	private JFrame getFrame() {
		if (frame == null) {
			frame = new JFrame();
		}
		return frame;
	}

	private PinYinProgram() {
	}

	private void init(final Container container) {
		//
		final String wrap = "span 2,wrap";
		//
		add(container, new JLabel("Input"));
		add(container, tfInput = new JTextField(), wrap);
		//
		add(container, new JLabel(""));
		add(container, btnExecute = new JButton("Execute"), wrap);
		btnExecute.addActionListener(this);
		//
		add(container, new JLabel("Result"));
		add(container, tfResult = new JTextField());
		tfResult.setEditable(false);
		add(container, btnCopy = new JButton("Copy"), "wrap");
		btnCopy.addActionListener(this);
		//
		final int width = 200;
		tfInput.setPreferredSize(new Dimension(width, (int) tfInput.getPreferredSize().getHeight()));
		tfResult.setPreferredSize(new Dimension(width, (int) tfResult.getPreferredSize().getHeight()));
		//
	}

	public void actionPerformed(final ActionEvent evt) {
		//
		final Object source = evt != null ? evt.getSource() : null;
		//
		if (Objects.equals(source, btnExecute)) {
			tfResult.setText(com.heimuheimu.util.pinyin.PinyinUtil.toPinyinWithoutTone(tfInput.getText()));
		} else if (Objects.equals(source, btnCopy)) {
			final StringSelection selection = new StringSelection(tfResult.getText());
			final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			if (clipboard != null) {
				clipboard.setContents(selection, selection);
			}
		}
		//
	}

	private static void add(final Container container, final Component component) {
		if (container != null) {
			container.add(component);
		}
	}

	private static void add(final Container container, final Component component, final Object object) {
		if (container != null) {
			container.add(component, object);
		}
	}

	public static void main(final String[] args) {
		//
		final PinYinProgram instance = new PinYinProgram();
		final JFrame frame = instance.getFrame();
		//
		if (frame != null) {
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLayout(new MigLayout());
			instance.init(frame);
			frame.pack();
			frame.setVisible(true);
		}
		//
	}

}
