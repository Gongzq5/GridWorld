// package Cal;

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.math.BigDecimal;  
  
public class Calculator extends JFrame {
	private int opId;
	private int index;
	private boolean newSecond, CaluOnce;
	private double result;
	private JButton[] buttonOfOps;
	private JButton[] buttonOfNums;
	private JTextArea textOfFirstNum, textOfSecondNum, textOfResultNum, textOfOp;

	public Calculator() {

		Font font=new Font("Courier New",Font.PLAIN,36);
		setFont(font);

		CaluOnce = false;
		index = 0;
		newSecond = true;
		buttonOfOps = new JButton[6];
		buttonOfNums = new JButton[11];
		
		textOfFirstNum = new JTextArea();
		textOfFirstNum.setBorder(BorderFactory.createLineBorder(Color.gray, 10));
		textOfFirstNum.setEditable(false);
		textOfSecondNum = new JTextArea();
		textOfSecondNum.setBorder(BorderFactory.createLineBorder(Color.gray, 10));
		textOfSecondNum.setEditable(false);
		textOfResultNum = new JTextArea();
		textOfResultNum.setBorder(BorderFactory.createLineBorder(Color.gray, 10));
		textOfResultNum.setEditable(false);
		textOfOp = new JTextArea();
		textOfOp.setBorder(BorderFactory.createLineBorder(Color.gray, 10));
		textOfOp.setEditable(false);

		JPanel numsPanel = new JPanel(), opsPanel = new JPanel(), eqPanel = new JPanel();

		buttonOfOps[0] = new JButton("+");
		buttonOfOps[1] = new JButton("-");
		buttonOfOps[2] = new JButton("*");
		buttonOfOps[3] = new JButton("/");
		buttonOfOps[4] = new JButton("=");
		buttonOfOps[5] = new JButton("AC");
		
		numsPanel.setLayout(new GridLayout(4,3));
		opsPanel.setLayout(new GridLayout(4,2));
		eqPanel.setLayout(new GridLayout(1,4));
		

		for (int i=0; i<10; i++) {
			buttonOfNums[i]=new JButton(String.valueOf(i));
			final int t = i;
			buttonOfNums[i].setFocusable(false);
			buttonOfNums[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {									
					if (index == 0) {
						if (t == 0 && textOfFirstNum.getText() == "") return;
						textOfFirstNum.setText(textOfFirstNum.getText() + String.valueOf(t));
					}
					else if (index == 1) 
						index = 2;
					if (index == 2) {
						if (t == 0 && textOfSecondNum.getText() == "") return;

						textOfSecondNum.setText(newSecond ? String.valueOf(t) : textOfSecondNum.getText() + String.valueOf(t));
						newSecond = false;
					}
				}
			});
		}
		buttonOfNums[10] = new JButton(".");
		buttonOfNums[10].setFocusable(false);
		buttonOfNums[10].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {									
				if (index == 0) {
					if (textOfFirstNum.getText() == "") return;
					textOfFirstNum.setText(textOfFirstNum.getText() + ".");
				} else if (index == 1) {
					index = 2;
				}

				if (index == 2) {
					if (textOfSecondNum.getText() == "") return;
					textOfSecondNum.setText(textOfSecondNum.getText() + ".");
					newSecond = false;
				}
			}
		});

		for (int i=1; i<=11; i++) {
			if (i == 10) {
				numsPanel.add(buttonOfNums[0]);
			} else if (i == 11) {
				numsPanel.add(buttonOfNums[10]);
			} else {
				numsPanel.add(buttonOfNums[i]);
			}
		}

		buttonOfOps[0].setFocusable(false);
		buttonOfOps[0].addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {  
                if (textOfFirstNum.getText() == "") {
                	index = 0;
                } else {
                	if (CaluOnce) {
                		textOfFirstNum.setText(String.valueOf(result));
                		textOfSecondNum.setText("");
                		textOfResultNum.setText("");
                		CaluOnce = false;
                	}
                    index = 1;
                	textOfOp.setText("+");
                }
                opId = 0;
                newSecond = true;
            }
		});
		buttonOfOps[1].setFocusable(false);
		buttonOfOps[1].addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                if (textOfFirstNum.getText() == "") {
                	index = 0;
                } else {
                	if (CaluOnce) {
                		textOfFirstNum.setText(String.valueOf(result));
                		textOfSecondNum.setText("");
                		textOfResultNum.setText("");
                		CaluOnce = false;
                	}
                    index = 1;
                	textOfOp.setText("-");
                }
                opId = 1;
                newSecond = true;
            }
		});
		buttonOfOps[2].setFocusable(false);
		buttonOfOps[2].addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                if (textOfFirstNum.getText() == "") {
                	index = 0;
                } else {
                	if (CaluOnce) {
                		textOfFirstNum.setText(String.valueOf(result));
                		textOfSecondNum.setText("");
                		textOfResultNum.setText("");
                		CaluOnce = false;
                	}
                    index = 1;
                	textOfOp.setText("*");
                }
                opId = 2;
                newSecond = true;
            }
		});
		buttonOfOps[3].setFocusable(false);
		buttonOfOps[3].addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                if (textOfFirstNum.getText() == "") {
                	index = 0;
                } else {
                	if (CaluOnce) {
                		textOfFirstNum.setText(String.valueOf(result));
                		textOfSecondNum.setText("");
                		textOfResultNum.setText("");
                		CaluOnce = false;
                	}
                    index = 1;
                	textOfOp.setText("/");
                }
                opId = 3;
                newSecond = true;
            }
		});
		buttonOfOps[4].setFocusable(false);
		buttonOfOps[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
            	if (textOfFirstNum.getText() == "") {
            		return;
            	}      
                double first = Double.parseDouble(textOfFirstNum.getText());
                double second = Double.parseDouble(textOfSecondNum.getText());

                result = 0;
                switch (opId) {
                	case 0: result = first + second; break;
                	case 1: result = first - second; break;
                	case 2: result = first * second; break;
                	case 3: result = first / second; break;
                	default: break;
                }
                textOfResultNum.setText(String.valueOf(result));
                CaluOnce = true;
                index = 0;
            }
        });
		buttonOfOps[5].setFocusable(false);
		buttonOfOps[5].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textOfResultNum.setText("");
				textOfFirstNum.setText("");
				textOfSecondNum.setText("");
				textOfOp.setText("");
				index = 0;
				CaluOnce = false;
			}
		});

		for (int i=0; i<6; i++) {
			opsPanel.add(buttonOfOps[i]);
		}
		eqPanel.add(textOfFirstNum);
		eqPanel.add(textOfOp);
		eqPanel.add(textOfSecondNum);
		eqPanel.add(textOfResultNum);
		
		this.add(eqPanel, BorderLayout.NORTH);
		this.add(numsPanel, BorderLayout.CENTER);
		this.add(opsPanel, BorderLayout.EAST);
	}

	public int Add(int a, int b) {
		return a+b;
	}

	public static void main(String[] args) {
		Calculator window = new Calculator();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBackground(Color.gray);
		window.setTitle("Easy Calculator");
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.setSize(500,300);
		window.setVisible(true);
		return;
	}
}


