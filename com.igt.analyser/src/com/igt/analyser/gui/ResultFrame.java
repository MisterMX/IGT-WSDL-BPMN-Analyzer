package com.igt.analyser.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.igt.analyser.BPMNActivity;
import com.igt.analyser.WSDLServiceRecommendation;

import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.JSplitPane;
import javax.swing.ListModel;

import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.TextArea;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class ResultFrame extends JFrame {

	private JPanel contentPane;
	private TextArea textAreaResult;
	private BPMNActivity[] bpmnActivities;
	
	/**
	 * Create the frame.
	 */
	public ResultFrame(BPMNActivity[] bpmnActivities, BufferedImage image) {
		this.bpmnActivities = bpmnActivities;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.2);
		contentPane.add(splitPane);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);
		
		ImagePanel imagePanel = new ImagePanel();
		imagePanel.setImage(image);
		scrollPane.setViewportView(imagePanel);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setResizeWeight(0.5);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel.add(splitPane_1, BorderLayout.CENTER);
		
		JList listActivities = new JList(bpmnActivities);
		listActivities.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				 showDetails(listActivities.getSelectedIndex());
			}
		});
		splitPane_1.setLeftComponent(listActivities);
		
		textAreaResult = new TextArea();
		textAreaResult.setEditable(false);
		splitPane_1.setRightComponent(textAreaResult);
	}
	
	private void showDetails(int activityIndex) {
		if (activityIndex >= 0 && activityIndex < bpmnActivities.length) {
			StringBuilder stringBuilder = new StringBuilder();
			
			BPMNActivity activity = bpmnActivities[activityIndex];
			
			stringBuilder.append(String.format("%d service(s) found: \n", activity.recommendations.size()));
			
			for (WSDLServiceRecommendation recommendation : activity.recommendations) {
				if (recommendation.isRelevant) {
					stringBuilder.append("\t+ ");
				} else {
					stringBuilder.append("\t- ");
				}
				
				stringBuilder.append(String.format("%1$s (%2$.2f %%)\n", recommendation.serviceName, recommendation.similarity * 100));
			}
			
			stringBuilder.append(String.format(" - Recall: %1$.2f %%\n", activity.recall));
			stringBuilder.append(String.format(" - Precision: %1$.2f %%\n", activity.precision));
			stringBuilder.append(String.format(" - F-Measure: %1$.2f %%\n", activity.fScore));
			
			textAreaResult.setText(stringBuilder.toString());
		} else {
			textAreaResult.setText("");
		}
	}
}
