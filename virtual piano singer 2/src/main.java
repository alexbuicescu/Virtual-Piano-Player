import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.AWTException;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JCheckBox;

public class main {

	public Robot robocop = null;
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	public int SpaceTime = 350, LineTime = 350, PlayTime = 3000, NotesTime = 280;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main window = new main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public main() {
		initialize();
	}
	
	public int CharAction(char caracter)
	{
		switch (caracter)
		{
			case ' ': return -2;
			case '-': return -3;
			case 'a': return KeyEvent.VK_A;
			case 'b': return KeyEvent.VK_B;
			case 'c': return KeyEvent.VK_C;
			case 'd': return KeyEvent.VK_D;
			case 'e': return KeyEvent.VK_E;
			case 'f': return KeyEvent.VK_F;
			case 'g': return KeyEvent.VK_G;
			case 'h': return KeyEvent.VK_H;
			case 'i': return KeyEvent.VK_I;
			case 'j': return KeyEvent.VK_J;
			case 'k': return KeyEvent.VK_K;
			case 'l': return KeyEvent.VK_L;
			case 'm': return KeyEvent.VK_M;
			case 'n': return KeyEvent.VK_N;
			case 'o': return KeyEvent.VK_O;
			case 'p': return KeyEvent.VK_P;
			case 'q': return KeyEvent.VK_Q;
			case 'r': return KeyEvent.VK_R;
			case 's': return KeyEvent.VK_S;
			case 't': return KeyEvent.VK_T;
			case 'u': return KeyEvent.VK_U;
			case 'v': return KeyEvent.VK_V;
			case 'x': return KeyEvent.VK_X;
			case 'y': return KeyEvent.VK_Y;
			case 'z': return KeyEvent.VK_Z;
			case 'w': return KeyEvent.VK_W;
			
			case '0': return KeyEvent.VK_0;
			case '1': return KeyEvent.VK_1;
			case '2': return KeyEvent.VK_2;
			case '3': return KeyEvent.VK_3;
			case '4': return KeyEvent.VK_4;
			case '5': return KeyEvent.VK_5;
			case '6': return KeyEvent.VK_6;
			case '7': return KeyEvent.VK_7;
			case '8': return KeyEvent.VK_8;
			case '9': return KeyEvent.VK_9;
		}
		return -1;
	}

	public int CharActionShift(char caracter)
	{
		switch (caracter)
		{
			case 'A': return KeyEvent.VK_A;
			case 'B': return KeyEvent.VK_B;
			case 'C': return KeyEvent.VK_C;
			case 'D': return KeyEvent.VK_D;
			case 'E': return KeyEvent.VK_E;
			case 'F': return KeyEvent.VK_F;
			case 'G': return KeyEvent.VK_G;
			case 'H': return KeyEvent.VK_H;
			case 'I': return KeyEvent.VK_I;
			case 'J': return KeyEvent.VK_J;
			case 'K': return KeyEvent.VK_K;
			case 'L': return KeyEvent.VK_L;
			case 'M': return KeyEvent.VK_M;
			case 'N': return KeyEvent.VK_N;
			case 'O': return KeyEvent.VK_O;
			case 'P': return KeyEvent.VK_P;
			case 'Q': return KeyEvent.VK_Q;
			case 'R': return KeyEvent.VK_R;
			case 'S': return KeyEvent.VK_S;
			case 'T': return KeyEvent.VK_T;
			case 'U': return KeyEvent.VK_U;
			case 'V': return KeyEvent.VK_V;
			case 'X': return KeyEvent.VK_X;
			case 'Y': return KeyEvent.VK_Y;
			case 'Z': return KeyEvent.VK_Z;
			case 'W': return KeyEvent.VK_W;
			
			case ')': return KeyEvent.VK_0;
			case '!': return KeyEvent.VK_1;
			case '@': return KeyEvent.VK_2;
			case '#': return KeyEvent.VK_3;
			case '$': return KeyEvent.VK_4;
			case '%': return KeyEvent.VK_5;
			case '^': return KeyEvent.VK_6;
			case '&': return KeyEvent.VK_7;
			case '*': return KeyEvent.VK_8;
			case '(': return KeyEvent.VK_9;
		}
		return -1;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		final boolean stop = false;
		try {
			robocop = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		frame = new JFrame();
		frame.setBounds(100, 100, 725, 444);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		final JCheckBox chckbxNewCheckBox = new JCheckBox("Repeat");
		chckbxNewCheckBox.setBounds(439, 200, 75, 25);
		frame.getContentPane().add(chckbxNewCheckBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 52, 419, 274);
		frame.getContentPane().add(scrollPane);
		
		final JTextPane musicSheet = new JTextPane();
		scrollPane.setViewportView(musicSheet);
		
		JButton btnNewButton_1 = new JButton("Play");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
//				KeyEvent keyEvt = null;//KeyEventRecorder.getLastEvent();

				robocop.delay(PlayTime);
				while(chckbxNewCheckBox.isSelected())
				{
					if(MouseInfo.getPointerInfo().getLocation().x < 10 &&
							MouseInfo.getPointerInfo().getLocation().y < 10)
					{
						break;
					}
					robocop.delay(SpaceTime);
					
					int sheetSize = musicSheet.getText().length();
					String text = musicSheet.getText();
					for(int i = 0; i < musicSheet.getText().length(); i++)
					{
						if(MouseInfo.getPointerInfo().getLocation().x < 10 &&
								MouseInfo.getPointerInfo().getLocation().y < 10)
						{
							break;
						}
						
						if(text.charAt(i) != '[')// && text.charAt(i) != ']')
						{
							int charAct = CharAction(text.charAt(i));
							if(charAct == -1)
							{
								int charActShift = CharActionShift(text.charAt(i));
								if(charActShift != -1)
								{
									robocop.keyPress(KeyEvent.VK_SHIFT);
									robocop.keyPress(charActShift);
									robocop.keyRelease(charActShift);
									robocop.keyRelease(KeyEvent.VK_SHIFT);
									robocop.delay(NotesTime);
								}
							}
							else
								if(charAct == -2)
								{
									robocop.delay(SpaceTime);
								}
								else
									if(charAct == -3)
									{
										robocop.delay(LineTime);
									}
									else
									{
										robocop.keyPress(charAct);
										robocop.keyRelease(charAct);
										robocop.delay(NotesTime);
									}
						}
						else
						{
							int taste[] = new int[5];
							int nr = 0;
							boolean isShiftPressed = false;
							
							int j = i + 1;
							while(text.charAt(j) != ']')
							{
								int charAct = CharAction(text.charAt(j));
								if(charAct == -1)
								{
									charAct = CharActionShift(text.charAt(j));
									if(charAct != -1)
									{
										isShiftPressed = true;
									}
								}
								taste[nr] = charAct;
								j++;
								nr++;
							}
							
							if(isShiftPressed)
							{
								robocop.keyPress(KeyEvent.VK_SHIFT);
							}
							for(int k = 0; k < nr; k++)
							{
								robocop.keyPress(taste[k]);
							}
							for(int k = 0; k < nr; k++)
							{
								robocop.keyRelease(taste[k]);
							}
							robocop.keyRelease(KeyEvent.VK_SHIFT);
							robocop.delay(NotesTime);
							
							i = j;
						}
					}
				}
			}
		});
		btnNewButton_1.setBounds(237, 13, 97, 25);
		frame.getContentPane().add(btnNewButton_1);
		

		JButton btnNewButton = new JButton("Load Song");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

                JFileChooser openFile = new JFileChooser();
                openFile.showOpenDialog(null);
                java.io.File file = openFile.getSelectedFile();
                //System.out.println(file.toString());
                
				BufferedReader br = null;
				String everything = "";
				try {
					br = new BufferedReader(new FileReader(file.toString()));
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			    try {
			        StringBuilder sb = new StringBuilder();
			        String line = br.readLine();

			        while (line != null) {
			            sb.append(line);
			            sb.append('\n');
			            line = br.readLine();
			        }
			        everything = sb.toString();
			    } catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
			        try {
						br.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    }
			    musicSheet.setText(everything);
			}
		});

		btnNewButton.setBounds(25, 13, 139, 25);
		frame.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setText("3000");
		textField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {		  
			  }
			  public void removeUpdate(DocumentEvent e) {
				  if(!textField.getText().equals(""))
				  {
					  PlayTime = Integer.parseInt(textField.getText());		
				  }
			  }
			  public void insertUpdate(DocumentEvent e) {
				  if(!textField.getText().equals(""))
				  {
					  PlayTime = Integer.parseInt(textField.getText());		
				  }
			  }
		});
		textField.setBounds(566, 73, 75, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Time before playing");
		lblNewLabel.setBounds(437, 76, 139, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblTimeForSpace = new JLabel("Time for space(\" \")");
		lblTimeForSpace.setBounds(437, 111, 139, 16);
		frame.getContentPane().add(lblTimeForSpace);
		
		textField_1 = new JTextField();
		textField_1.setText("350");
		textField_1.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {			  
			  }
			  public void removeUpdate(DocumentEvent e) {
				  if(!textField_1.getText().equals(""))
				  {
					  SpaceTime = Integer.parseInt(textField_1.getText());	
				  }
			  }
			  public void insertUpdate(DocumentEvent e) {
				  if(!textField_1.getText().equals(""))
				  {
					  SpaceTime = Integer.parseInt(textField_1.getText());	
				  }
			  }
		});
		textField_1.setColumns(10);
		textField_1.setBounds(566, 108, 75, 22);
		frame.getContentPane().add(textField_1);
		
		JLabel lblTimeFor = new JLabel("Time for \"-\"");
		lblTimeFor.setBounds(437, 143, 139, 16);
		frame.getContentPane().add(lblTimeFor);
		
		textField_2 = new JTextField();
		textField_2.setText("350");
		textField_2.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			  }
			  public void removeUpdate(DocumentEvent e) {
				  if(!textField_2.getText().equals(""))
				  {
					  LineTime = Integer.parseInt(textField_2.getText());
				  }
			  }
			  public void insertUpdate(DocumentEvent e) {
				  if(!textField_2.getText().equals(""))
				  {
					  LineTime = Integer.parseInt(textField_2.getText());
				  }
			  }
		});
		textField_2.setColumns(10);
		textField_2.setBounds(566, 140, 75, 22);
		frame.getContentPane().add(textField_2);
		
		JLabel lblTimeBetweenNotes = new JLabel("Time between notes");
		lblTimeBetweenNotes.setBounds(437, 175, 139, 16);
		frame.getContentPane().add(lblTimeBetweenNotes);
		
		textField_3 = new JTextField();
		textField_3.setText("280");
		textField_3.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {	  
			  }
			  public void removeUpdate(DocumentEvent e) {
				  if(!textField_3.getText().equals(""))
				  {
					  NotesTime = Integer.parseInt(textField_3.getText());			
				  }
			  }
			  public void insertUpdate(DocumentEvent e) {
				  if(!textField_3.getText().equals(""))
				  {
					  NotesTime = Integer.parseInt(textField_3.getText());			
				  }
			  }
		});
		textField_3.setColumns(10);
		textField_3.setBounds(566, 172, 75, 22);
		frame.getContentPane().add(textField_3);
		
		JLabel lblNewLabel_1 = new JLabel("The time is expressed in ms!");
		lblNewLabel_1.setBounds(445, 52, 194, 16);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
