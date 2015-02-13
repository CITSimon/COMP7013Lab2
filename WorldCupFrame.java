import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WorldCupFrame extends JFrame 
{
	/**
	*  The "scope" (i.e. What can see it) of these instance variables is the entire
	 * class and any inner classes it may have. So other methods and inner class can see it. This is important 
	 * in this case for the ActionListener inner class to compare the source of the ActionEvent
	 * with one of these JButton references to see where it came from.
	 */
	private JButton addButton;
	private JButton editButton;
	private JButton deleteButton;
	private JButton okButton;
	private JButton cancelButton;
	
	public WorldCupFrame(String title)
	{
		super(title);
		//This is what we are going to use as the 
		//content of our JFrame
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JPanel sidePanel = createSideButtonPanel();		
		mainPanel.add(sidePanel, BorderLayout.EAST);
		
		JPanel bottomPanel = createBottomButtonPanel();
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		getContentPane().add(mainPanel);
	}
	
	/**
	 * This method creates the bottom JPanel, puts buttons on it and returns the fully constructed JPanel
	 */
	private JPanel createBottomButtonPanel()
	{
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		
		return buttonPanel;
	}
	
	private JPanel createSideButtonPanel()
	{
		addButton = new JButton("Add");
		editButton = new JButton("Edit");
		deleteButton = new JButton("Delete");
		
		/**
		 * Create an instance of inner class SideButtonsActionListener
		 * When we create an instance of the inner class we pass
		 * it a reference to its containing class (i.e. WorldCupFrame) so the
		 * inner class can reference its containing class.
		 */
		SideButtonsActionListener buttonListener = 
				new SideButtonsActionListener(this);
		
		addButton.addActionListener(buttonListener);
		editButton.addActionListener(buttonListener);
		deleteButton.addActionListener(buttonListener);
		
		JPanel sideButtonPanel = new JPanel();
		
		BoxLayout boxL = new BoxLayout(sideButtonPanel, BoxLayout.Y_AXIS);
		sideButtonPanel.setLayout(boxL);
////////// Can also be written like this in one line/////////
//		sideButtonPanel.setLayout(
//		new BoxLayout(sideButtonPanel, BoxLayout.X_AXIS));

		sideButtonPanel.add(addButton);
		sideButtonPanel.add(Box.createVerticalStrut(5));
		sideButtonPanel.add(editButton);
		sideButtonPanel.add(Box.createVerticalStrut(5));
		sideButtonPanel.add(deleteButton);
		
		return sideButtonPanel;
	}
	
	//Inner class implementation of ActionListener
	private class SideButtonsActionListener implements ActionListener
	{
		//This is to allow this inner class to refer to its 
		//containing class (i.e. WorldCupFrame)
		private WorldCupFrame outerClass;
		
		public SideButtonsActionListener(WorldCupFrame outerClass)
		{
			this.outerClass = outerClass;
		}
		
		public void actionPerformed(ActionEvent e)
		{
			//We know that the source of any ActionEvent
			//in this program MUST be a JButton seeing as
			//we only added an instance of this listener to 
			//JButton objects
			JButton sourceButton = (JButton)e.getSource();
			if(sourceButton.equals(addButton))
			{
				AddPlayerDialog addPlyrDlg = 
							new AddPlayerDialog(this.outerClass, "Add Player");
				addPlyrDlg.setSize(200, 300);
				addPlyrDlg.setVisible(true);
			}
			else if(sourceButton.equals(editButton))
			{
				System.out.println("Edit button clicked");
			}
			else 
			{
				System.out.println("Delete button clicked");
			}
				
		}
	}
	
}
