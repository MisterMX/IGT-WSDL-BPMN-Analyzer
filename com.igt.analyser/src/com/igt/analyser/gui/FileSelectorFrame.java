package com.igt.analyser.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.igt.analyser.Analyser;
import com.igt.analyser.AnalyserImpl;
import com.igt.analyser.BPMNActivity;
import com.igt.analyser.BPMNParser;
import com.igt.analyser.BPMNParserImpl;
import com.igt.analyser.TestService;
import com.igt.analyser.TestServiceImpl;
import com.igt.analyser.WSDLParser;
import com.igt.analyser.WSDLParserImpl;
import com.igt.analyser.WSDLServiceRecommendation;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.imageio.plugins.bmp.BMPImageWriteParam;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.awt.event.ActionEvent;

public class FileSelectorFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldBpmn;
	private JTextField textFieldImage;
	private JTextField textFieldWsdl;
	private JTextField textFieldComp;

	/**
	 * Create the frame.
	 */
	public FileSelectorFrame() {
		setTitle("IGT BPMN Analyzer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{424, 0};
		gbl_contentPane.rowHeights = new int[]{47, 47, 47, 47, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		contentPane.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblBpmn = new JLabel("Signavio XML:  ");
		lblBpmn.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(lblBpmn, BorderLayout.WEST);
		
		textFieldBpmn = new JTextField();
		panel_1.add(textFieldBpmn, BorderLayout.CENTER);
		textFieldBpmn.setColumns(40);
		
		JButton btnBpmnFile = new JButton("...");
		btnBpmnFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openFileSelectionDialog(textFieldBpmn);
			}
		});
		panel_1.add(btnBpmnFile, BorderLayout.EAST);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		contentPane.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblBpmnImage = new JLabel("BPMN image: ");
		lblBpmnImage.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(lblBpmnImage, BorderLayout.WEST);
		
		textFieldImage = new JTextField();
		textFieldImage.setColumns(33);
		panel_2.add(textFieldImage);
		
		JButton btnImgFile = new JButton("...");
		btnImgFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openFileSelectionDialog(textFieldImage);
			}
		});
		btnImgFile.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(btnImgFile, BorderLayout.EAST);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 2;
		contentPane.add(panel_3, gbc_panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JLabel lblWsdl = new JLabel("WSDL: ");
		lblWsdl.setHorizontalAlignment(SwingConstants.LEFT);
		panel_3.add(lblWsdl, BorderLayout.WEST);
		
		textFieldWsdl = new JTextField();
		textFieldWsdl.setColumns(20);
		panel_3.add(textFieldWsdl, BorderLayout.CENTER);
		
		JButton btnWsdlFile = new JButton("...");
		btnWsdlFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openFileSelectionDialog(textFieldWsdl);
			}
		});
		panel_3.add(btnWsdlFile, BorderLayout.EAST);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 3;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTestFile = new JLabel("Comparison XML: ");
		lblTestFile.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblTestFile, BorderLayout.WEST);
		
		textFieldComp = new JTextField();
		textFieldComp.setColumns(40);
		panel.add(textFieldComp, BorderLayout.CENTER);
		
		JButton btnCompFile = new JButton("...");
		btnCompFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openFileSelectionDialog(textFieldComp);
			}
		});
		panel.add(btnCompFile, BorderLayout.EAST);
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 4;
		contentPane.add(panel_4, gbc_panel_4);
		
		JButton btnAnalyze = new JButton("Analyze");
		btnAnalyze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				analyzeAndShowResult();
			}
		});
		panel_4.add(btnAnalyze);
	}
	
	private void openFileSelectionDialog(JTextField textField) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(false);
		
		File preselectedFile = new File(textFieldComp.getText());
		
		if (preselectedFile.exists()) {
			fileChooser.setSelectedFile(preselectedFile);
		}
		
		if (fileChooser.showOpenDialog(FileSelectorFrame.this) == JFileChooser.APPROVE_OPTION) {
			textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
		}
	}
	
	private void analyzeAndShowResult() {
		try {
			analyze();
		} catch(Exception ex) {
			JOptionPane.showConfirmDialog(this, ex.getMessage(), "Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void analyze() throws ParserConfigurationException, SAXException, IOException {
//		File bpmnFile = new File("res/sig-test.bpmn"); // new File(args[0]);
//		File wsdlFile = new File("res/wheather.wsdl"); // new File(args[1]);
//		File testFile = new File("res/wheather-test.xml"); // new File(args[2]);
		
		File bpmnFile = new File(textFieldBpmn.getText());
		File wsdlFile = new File(textFieldWsdl.getText());
		File testFile = new File(textFieldComp.getText());
		File imageFile = new File(textFieldImage.getText());
		
		if (!bpmnFile.exists() || !wsdlFile.exists() || !testFile.exists() || !imageFile.exists()) {
			throw new IOException("Please select a file for all entries.");
		}
		
		WSDLParser wsdlService = new WSDLParserImpl();
		BPMNParser bpmnService = new BPMNParserImpl();
		Analyser analyser = new AnalyserImpl();
		TestService testService = new TestServiceImpl();

		String[] serviceNames = wsdlService.getServiceNames(wsdlFile.getAbsolutePath());
		String[] activityNames = bpmnService.getActivities(bpmnFile.getAbsolutePath());
		Map<String, Set<String>> activityServiceConnections = testService.getActivityServiceMapping(testFile.getAbsolutePath());
		
		BPMNActivity[] activities = analyser.getRecommendedServices(activityNames, serviceNames);
		
		for (BPMNActivity activity : activities) {
			System.out.println(activity.name);
			
			Set<String> relevantServices = activityServiceConnections.get(activity.name);
			
			int relevantServiceCount = 0;
			
			System.out.println(String.format(" %d service(s) found.", activity.recommendations.size()));
			for (WSDLServiceRecommendation recommendation : activity.recommendations) {
				String serviceText = String.format("%1$s (%2$.2f %%)", recommendation.serviceName, recommendation.similarity * 100);

				// Is relevant?
				if (relevantServices != null && relevantServices.contains(recommendation.serviceName)) {
					relevantServiceCount++;
					System.out.println("\t+ " + serviceText);
					recommendation.isRelevant = true;
				} else {
					System.out.println("\t- " + serviceText);
					recommendation.isRelevant = false;
				}
			}
			
			activity.relevantServiceCount = relevantServiceCount;
			activity.precision = activity.recommendations.size() > 0
					? (double)relevantServiceCount / activity.recommendations.size() * 100
					: 0;
			activity.recall = relevantServices != null
					? (double)relevantServiceCount / relevantServices.size() * 100
					: 0;
			
			activity.fScore = activity.precision + activity.recall != 0f
					? 2 * activity.precision * activity.recall / (activity.precision + activity.recall)
					: 0;
			
			System.out.println(String.format(" - Recall: %1$.2f %%", activity.recall));
			System.out.println(String.format(" - Precision: %1$.2f %%", activity.precision));
			System.out.println(String.format(" - F-Measure: %1$.2f %%", activity.fScore));
			System.out.println();
		}
		
		BufferedImage bufferedImage = ImageIO.read(imageFile);
		
		ResultFrame resultFrame = new ResultFrame(activities, bufferedImage);
		resultFrame.setVisible(true);
		
		this.setVisible(false);
	}
}
