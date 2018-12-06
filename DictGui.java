import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DictGui extends JFrame {

	private JPanel contentPane;
	private JTextField t1;
	private JTextField t2;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DictGui frame = new DictGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DictGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		File file=new File("e:\\dict.txt");
		 File tfile=new File("e:\\dict1.txt");
		t1 = new JTextField();
		t1.setBounds(52, 40, 135, 29);
		contentPane.add(t1);
		t1.setColumns(10);
		
		JButton add = new JButton("ADD");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  try {
				  FileWriter fw=new FileWriter(file,true);
				  BufferedWriter br=new BufferedWriter(fw);
				  br.write(t1.getText()+":"+t2.getText()+"#");
				  br.close();
				  fw.close();
				  }
				  catch(IOException ob)
				{
					System.out.println(ob.toString());
				}
				  JOptionPane.showMessageDialog(null, "Word Added");
				  t1.setText("");
					t2.setText("");
				}
		});
		add.setBounds(217, 43, 89, 23);
		contentPane.add(add);
		
		t2 = new JTextField();
		t2.setBounds(52, 120, 135, 98);
		contentPane.add(t2);
		t2.setColumns(10);
		
		JButton search = new JButton("SEARCH");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  try {
				   int z=0;
				  FileReader fr=new FileReader(file);
				 int q;
				  do
				  {
					String  s="";
					  do
					  {
						  q=fr.read();
						  if(q==-1)
							  break;
						  char ch=(char)q;
						  if(ch!=':')
							  s=s+ch;
						  else
							  break;
					  }while(true);
					  if(q==-1)
						  break;
					  String s1="";
					  do
					  {
						  char ch=(char)fr.read();
						  if(ch!='#')
							  s1=s1+ch;
						  else
							  break;
					  }while(true);
					  if(s.equals(t1.getText()))
					  {
						 t2.setText(s1);
						  z=1;
						 break;
					  }
				  }while(true);
				  if(z==0)
					  JOptionPane.showMessageDialog(null, "Word Not Found");
				  fr.close();
				  }
				  catch(IOException ob)
					{
						System.out.println(ob.toString());
					}
				
			}
		});
		search.setBounds(217, 93, 89, 23);
		contentPane.add(search);
		
		JButton delete = new JButton("DELETE");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					  int n;
					  FileReader fr1=new FileReader(file);
					  do
					  {
						 String  s="";
						  do
						  {
							  n=fr1.read();
							  if(n==-1)
								  break;
							 char ch=(char)n;
							  if(ch!=':')
								  s=s+ch;
							  else
								  break;
						  }while(true);
						  if(n==-1)
							  break;
						  String s1="";
						  do
						  {
							  char ch=(char)fr1.read();
							  if(ch!='#')
								  s1=s1+ch;
							  else
								  break;
						  }while(true);
						  if(s.equals(t1.getText()))
						  {
							  
						  }
						  else
						  {
							  FileWriter fwt=new FileWriter(tfile,true);
							  BufferedWriter brt=new BufferedWriter(fwt);
							  s=s+':'+s1+'#';
							  brt.write(s);
							  brt.close();
							  fwt.close();
							  JOptionPane.showMessageDialog(null, "Word Deleted");
						  }
					  }while(true);
					  fr1.close();
					  file.delete();
					  tfile.renameTo(file);
				}
				catch(IOException i)
				{
					System.out.println(i.toString());
				}
		
			}
		});
		delete.setBounds(217, 143, 89, 23);
		contentPane.add(delete);
		
		JLabel lblEnterWord = new JLabel("ENTER WORD");
		lblEnterWord.setBounds(67, 11, 89, 18);
		contentPane.add(lblEnterWord);
		
		JLabel lblItsMeaning = new JLabel("IT'S MEANING");
		lblItsMeaning.setBounds(67, 93, 90, 23);
		contentPane.add(lblItsMeaning);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			t1.setText("");
			t2.setText("");
			}
		});
		btnClear.setBounds(217, 195, 89, 23);
		contentPane.add(btnClear);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
