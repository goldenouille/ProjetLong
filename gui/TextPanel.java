package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.Highlighter.Highlight;
import javax.swing.text.JTextComponent.AccessibleJTextComponent;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.HTMLDocument;

import actions.ActClickText;

public class TextPanel extends AbstractPanel {

	private static final long serialVersionUID = 1L;
	private JTextPane textPane;
	private int[] lenghtTable;
	private boolean userText;

	public TextPanel(ClassicGuiController c, boolean userText) {
		super(c);
		this.userText = userText;
		this.setLayout(new BorderLayout());

		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setMargin(new Insets(10, 10, 10, 10));
		textPane.setContentType("text/html");
		lenghtTable = new int[0];
		textPane.addMouseListener(new ActClickText(controller));

		JScrollPane scrollPane = new JScrollPane(textPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		this.add(scrollPane, BorderLayout.CENTER);
	}

	public void replaceText(String[] text) {
		try {
			lenghtTable = new int[0];
			textPane.getStyledDocument().remove(0, textPane.getStyledDocument().getLength());
			apendText(text);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	public void apendText(String[] strings) {
		try {
			int[] newTable = new int[lenghtTable.length + strings.length];
			HTMLDocument document = (HTMLDocument) textPane.getDocument();
			//DefaultStyledDocument document = (DefaultStyledDocument) textPane.getStyledDocument();
			document.putProperty(DefaultEditorKit.EndOfLineStringProperty, "<br>\n");

			String str;
			for (int i = 0; i < lenghtTable.length + strings.length; i++) {
				if (i < lenghtTable.length) {
					newTable[i] = lenghtTable[i];
				} else {
					str = strings[i - lenghtTable.length];
					str = str.trim().replaceAll("\\\\n", "<br>");
					newTable[i] = ((i == 0) ? 0 : newTable[i - 1]) + str.length();
					str += " ";
					//document.insertString(document.getLength(), str + " ", null);
					document.insertAfterEnd(document.getCharacterElement(document.getLength()), str);
				}
			}
			lenghtTable = newTable;
		} catch (BadLocationException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Analyse la selection de l'utilisateur et la transforme en un tableau des
	 * pourcentage de selection de chaque mot
	 * 
	 * @return int[] un tableau de taille [nombre de mots] tel que tab[i] = %age
	 *         de selection du ieme mot
	 */
	public int[] getSelection() {
		int[] selection = new int[lenghtTable.length];
		int start = textPane.getSelectionStart();
		int end = textPane.getSelectionEnd();
		int i = 0;

		while (i < selection.length && start >= lenghtTable[i] + i) {
			selection[i] = 0;
			i++;
		}
		if (i >= selection.length)
			return selection;

		int premierMot = i;
		int erreurPremierMot = start - ((i == 0) ? 0 : lenghtTable[i - 1]) - i;

		while (i < selection.length && end > lenghtTable[i] + i + 1) {
			selection[i] = 100;
			i++;
		}
		if (i >= selection.length)
			return selection;

		int dernierMot = i;
		int erreurDernierMot = lenghtTable[i] + i - end;

		while (i < lenghtTable.length) {
			selection[i] = 0;
			i++;
		}

		if (premierMot == dernierMot) {
			selection[premierMot] = Math.min(100, 100 - ((100 * (erreurPremierMot + erreurDernierMot)) / (lenghtTable[premierMot] - ((premierMot == 0) ? 0
					: lenghtTable[premierMot - 1]))));
		} else {
			selection[premierMot] = Math.min(100, 100 - ((100 * erreurPremierMot) / (lenghtTable[premierMot] - ((premierMot == 0) ? 0
					: lenghtTable[premierMot - 1]))));
			selection[dernierMot] = Math.min(100, 100 - ((100 * erreurDernierMot) / (lenghtTable[dernierMot] - ((dernierMot == 0) ? 0
					: lenghtTable[dernierMot - 1]))));
		}

		return selection;
	}

	public int[] getKeywordPosition(Point point) throws BadLocationException {
		int[] keywordPosition = null;
		AccessibleJTextComponent context = ((AccessibleJTextComponent) textPane.getAccessibleContext());
		Highlight[] highlightsList = textPane.getHighlighter().getHighlights();
		// pour chaque highlight
		for (int i = 0; i < highlightsList.length; i++) {
			Highlight highlight = highlightsList[i];
			// si c'est un highlight de mot cle
			if (((DefaultHighlightPainter) highlight.getPainter()).getColor() == ClassicGuiController.VALIDATION_COLOR) {
				Rectangle keywordGraphic = context.getTextBounds(highlight.getStartOffset(), highlight.getEndOffset());
				// et que sa zone a l'ecran contient l'emplacement du clic droit
				if (keywordGraphic.contains(point)) {
					// on trouve son premier et dernier mot
					keywordPosition = new int[2];
					keywordPosition[0] = -1;
					keywordPosition[1] = -1;
					for (int j = 0; j < lenghtTable.length; j++) {
						if (keywordPosition[0] == -1 && highlight.getStartOffset() <= lenghtTable[j] + j)
							keywordPosition[0] = j;
						if (keywordPosition[1] == -1 && highlight.getEndOffset() <= lenghtTable[j] + j)
							keywordPosition[1] = j;
					}
					if (keywordPosition[0] == -1 || keywordPosition[1] == -1)
						throw new BadLocationException("keywordPosition = " + Arrays.toString(keywordPosition), 0);
				}
			}
		}
		return keywordPosition;
	}

	public String getText(int firstWord, int lastWord) throws BadLocationException {
		if (firstWord != 0)
			firstWord--;
		int start = lenghtTable[firstWord] + firstWord;
		int end = lenghtTable[lastWord] + lastWord;
		return textPane.getText(start, end - start).trim();
	}

	public void highlight(Color color, int firstWord, int lastWord) throws BadLocationException {
		int start = 0;
		if (firstWord != 0) {
			firstWord--;
			start = lenghtTable[firstWord] + firstWord;
		}
		if (textPane.getText(start, 1).equals(" "))
			start++;
		int end = lenghtTable[lastWord] + lastWord;
		textPane.getHighlighter().addHighlight(start, end, new DefaultHighlightPainter(color));
	}

	public void unHighlight(Color color, int firstWord, int lastWord) throws BadLocationException {
		int start = 0;
		if (firstWord != 0) {
			firstWord--;
			start = lenghtTable[firstWord] + firstWord;
		}
		int end = lenghtTable[lastWord] + lastWord;
		Highlight[] highlightsList = textPane.getHighlighter().getHighlights();
		ArrayList<HighlightData> newHighlights = new ArrayList<HighlightData>();

		for (int i = 0; i < highlightsList.length; i++) {
			Highlight highlight = highlightsList[i];
			if (((DefaultHighlightPainter) highlight.getPainter()).getColor() == color) {

				if (start <= highlight.getStartOffset() && end >= highlight.getEndOffset()) {
					// la selection contient tout le highlight
					textPane.getHighlighter().removeHighlight(highlight);

				} else if (start <= highlight.getStartOffset() && end > highlight.getStartOffset() && end < highlight.getEndOffset()) {
					// la selection contient le debut du highlight
					newHighlights.add(new HighlightData(end, highlight.getEndOffset(), color));
					textPane.getHighlighter().removeHighlight(highlight);

				} else if (start > highlight.getStartOffset() && start < highlight.getEndOffset() && end >= highlight.getEndOffset()) {
					// la selection contient la fin du highlight
					newHighlights.add(new HighlightData(highlight.getStartOffset(), start, color));
					textPane.getHighlighter().removeHighlight(highlight);

				} else if (start > highlight.getStartOffset() && end < highlight.getEndOffset()) {
					// la selection est contenue dans le highlight
					newHighlights.add(new HighlightData(highlight.getStartOffset(), start, color));
					newHighlights.add(new HighlightData(end, highlight.getEndOffset(), color));
					textPane.getHighlighter().removeHighlight(highlight);
				}
			}
		}

		// ajout des nouveaux highlights
		for (HighlightData data : newHighlights) {
			textPane.getHighlighter().addHighlight(data.start, data.end, new DefaultHighlightPainter(data.color));
		}
	}

	public void setTextFont(Font font) {
		MutableAttributeSet attrs = textPane.getInputAttributes();
		StyleConstants.setFontFamily(attrs, font.getFamily());
		StyleConstants.setFontSize(attrs, font.getSize());
		StyleConstants.setItalic(attrs, (font.getStyle() & Font.ITALIC) != 0);
		StyleConstants.setBold(attrs, (font.getStyle() & Font.BOLD) != 0);
		StyledDocument doc = textPane.getStyledDocument();
		doc.setCharacterAttributes(0, doc.getLength() + 1, attrs, true);
	}

	public JTextPane getTextPane() {
		return textPane;
	}

	public boolean isUserText() {
		return userText;
	}

	private class HighlightData {
		public int start, end;
		public Color color;

		public HighlightData(int start, int end, Color color) {
			this.start = start;
			this.end = end;
			this.color = color;
		}
	}

}
