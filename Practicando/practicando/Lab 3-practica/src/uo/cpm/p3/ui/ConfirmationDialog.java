package uo.cpm.p3.ui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ConfirmationDialog extends JDialog {
	private RegistryForm rF;
	private JLabel lblOk;
	private JLabel lblImageOk;
	private JTextField txtCode;
	private JLabel lblCode;
	private JButton btnFinish;

	/**
	 * Create the dialog.
	 * 
	 * @param registryForm
	 */
	public ConfirmationDialog(RegistryForm registryForm) {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ConfirmationDialog.class.getResource("/uo/cpm/p3/ui/img/logo.PNG")));
		setTitle("McDonald's: Confirmation dialog");
		rF = registryForm;
		setBounds(100, 100, 450, 231);
		getContentPane().setLayout(null);
		getContentPane().add(getLblOk());
		getContentPane().add(getLblImageOk());
		getContentPane().add(getTxtCode());
		getContentPane().add(getLblCode());
		getContentPane().add(getBtnFinish());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.getRootPane().setDefaultButton(btnFinish);

	}

	private JLabel getLblOk() {
		if (lblOk == null) {
			lblOk = new JLabel("Your order is being processed");
			lblOk.setBounds(141, 24, 153, 51);
		}
		return lblOk;
	}

	private JLabel getLblImageOk() {
		if (lblImageOk == null) {
			lblImageOk = new JLabel("");
			lblImageOk.setIcon(new ImageIcon(ConfirmationDialog.class.getResource("/uo/cpm/p3/ui/img/ok.png")));
			lblImageOk.setBounds(304, 24, 50, 51);
		}
		return lblImageOk;
	}

	private JTextField getTxtCode() {
		if (txtCode == null) {
			txtCode = new JTextField();
			txtCode.setEditable(false);
			txtCode.setBounds(151, 102, 130, 39);
			txtCode.setColumns(10);
			txtCode.setText(rF.getMc().getOrderCode());
		}
		return txtCode;
	}

	private JLabel getLblCode() {
		if (lblCode == null) {
			lblCode = new JLabel("Code:");
			lblCode.setLabelFor(getTxtCode());
			lblCode.setBounds(151, 79, 50, 13);
		}
		return lblCode;
	}

	private JButton getBtnFinish() {
		if (btnFinish == null) {
			btnFinish = new JButton("Finish");
			btnFinish.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					rF.getMw().getMc().saveOrder();
					rF.getMw().initialize();
				}
			});
			btnFinish.setBounds(177, 163, 85, 21);
		}
		return btnFinish;
	}
}
