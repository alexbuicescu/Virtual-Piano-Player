import java.awt.EventQueue;
s
import javax.print.DocFlavor.URL;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.AWTException;
import java.awt.Robot;
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

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class main {


	class MyThread extends Thread 
	{    
		boolean play = false;
		boolean pause = false;
	    public void run()
		{
	    	play = true;
	    	sing2();
	    	while(chckbxNewCheckBox.isSelected() && play)
			{
	    		sing2();
			}
	    }
	}

	public boolean is_playing = false;
	public JCheckBox chckbxNewCheckBox;
	public MyThread player_thread;
	public WebDriver driver;
	public WebElement element;
	public Robot robocop = null;
	private JFrame frame;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextPane musicSheet;
	
	
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
					 try {
				            // Set System L&F
				        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				    } 
				    catch (UnsupportedLookAndFeelException e) {
				       // handle exception
				    }
				    catch (ClassNotFoundException e) {
				       // handle exception
				    }
				    catch (InstantiationException e) {
				       // handle exception
				    }
				    catch (IllegalAccessException e) {
				       // handle exception
				    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void isWindows(String OS) {
		System.out.println(OS);
		if(OS.indexOf("in") >= 0)
		{
			File file = new File("chromedriver_windows/chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		}
	}
 
	public void isMac(String OS) {
		if(OS.indexOf("ac") >= 0)
		{
			File file = new File("chromedriver_mac/chromedriver");
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		}
	}
 
	public void isUnix(String OS) {
		if(OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 )
		{
			File file = new File("chromedriver_linux/chromedriver");
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());			
		}
	}
	/**
	 * Create the application.
	 */
	public main() {
		initialize();
	}
	public void sing2()
	{
		int sheetSize = musicSheet.getText().length();
		String text = musicSheet.getText();
		for(int i = 0; i < sheetSize; i++)
		{
			if(player_thread.play == false)
			{
				return;
			}
			
			if(player_thread.pause == true)
			{
				i--;
				continue;
			}
			
			if(text.charAt(i) != '[' && text.charAt(i) != '{')
			{
				if(text.charAt(i) != ' ' && text.charAt(i) != '-')
				{
					playnote(String.valueOf(text.charAt(i)));
					wait(NotesTime);
				}
				else
					if(text.charAt(i) == ' ')
					{
						wait(SpaceTime);
					}
					else
						if(text.charAt(i) == '-')
						{
							wait(LineTime);
						}
			}
			else
				if(text.charAt(i) == '[')
				{
					String combinatie = "";
					
					int j = i + 1;
					while(text.charAt(j) != ']')
					{
						combinatie += text.charAt(j);
						j++;
					}
					
					playnote(combinatie);
					wait(NotesTime);
										
					i = j;
				}
				else
					if(text.charAt(i) == '{')
					{
						int j = i + 1;
						String numar = "";
						while(text.charAt(j) != '}')
						{
							numar += text.charAt(j);
							j++;
						}
						int pauza = 0;
						try
						{
							pauza = Integer.parseInt(numar);
						}
						catch(Exception ex)
						{
						}
						wait(pauza);
						i = j;
					}
		}
	}

	void playnote(String c)
	{
		element.sendKeys(c);
	}
	
	void wait(int ms)
	{
		robocop.delay(ms);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
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
		
		chckbxNewCheckBox = new JCheckBox("Repeat");
		chckbxNewCheckBox.setBounds(459, 301, 75, 25);
		frame.getContentPane().add(chckbxNewCheckBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 52, 419, 274);
		frame.getContentPane().add(scrollPane);
		
		musicSheet = new JTextPane();
		scrollPane.setViewportView(musicSheet);
		
		JButton btnNewButton_1 = new JButton("Play / Pause");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(is_playing == false)
				{
					element = driver.switchTo().activeElement();
				
					player_thread = new MyThread();		
					player_thread.pause = false;
					player_thread.play = true;
					player_thread.start();
					
					is_playing = true;			
				}
				else
				{
					if(player_thread.pause == false)
					{
						player_thread.pause = true;
					}
					else
					{
						player_thread.pause = false;
					}
				}
			}
		});
		btnNewButton_1.setBounds(174, 13, 97, 25);
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
		
		JLabel lblTimeForSpace = new JLabel("Time for space(\" \")");
		lblTimeForSpace.setBounds(457, 212, 139, 16);
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
		textField_1.setBounds(586, 209, 75, 22);
		frame.getContentPane().add(textField_1);
		
		JLabel lblTimeFor = new JLabel("Time for \"-\"");
		lblTimeFor.setBounds(457, 244, 139, 16);
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
		textField_2.setBounds(586, 241, 75, 22);
		frame.getContentPane().add(textField_2);
		
		JLabel lblTimeBetweenNotes = new JLabel("Time between notes");
		lblTimeBetweenNotes.setBounds(457, 276, 139, 16);
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
		textField_3.setBounds(586, 273, 75, 22);
		frame.getContentPane().add(textField_3);
		
		JLabel lblNewLabel_1 = new JLabel("The time is represented in ms!");
		lblNewLabel_1.setBounds(447, 186, 194, 16);
		frame.getContentPane().add(lblNewLabel_1);

        
		isWindows(System.getProperty("os.name"));
		isMac(System.getProperty("os.name"));
		isUnix(System.getProperty("os.name"));
		driver = new ChromeDriver();
		driver.get("http://www.virtualpiano.net/");
		player_thread = new MyThread();
		
		JButton btnNewButton_2 = new JButton("Open Chrome");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				element = driver.switchTo().activeElement();

				player_thread = new MyThread();
				player_thread.play = true;
				player_thread.start();
					
			}
		});
		btnNewButton_2.setBounds(380, 14, 107, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				is_playing = false;
				player_thread.play = false;
				robocop.delay(100);
				player_thread = null;
			}
		});
		btnStop.setBounds(281, 14, 89, 23);
		frame.getContentPane().add(btnStop);
	}
}
