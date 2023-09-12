package view;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import model.ProjectDAO;

/**
 * A simple demonstration application showing how to create a Gantt chart.
 * <P>
 * This demo is intended to show the conceptual approach rather than being a
 * polished implementation.
 *
 *
 */
public class GraphView extends ApplicationFrame {
	private static int vNum = 0; 
	static ProjectDAO dao;
	static ArrayList list = null;
	/**
	 * Creates a new demo.
	 *
	 * @param title the frame title.
	 */
	public GraphView(final String title, int vNum) {
		super(title);

		this.vNum = vNum;
		try {
			dao = new ProjectDAO();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		final IntervalCategoryDataset dataset = createDataset(vNum);
        final JFreeChart chart = createChart(dataset);

        // ��Ʈ�� �гο� �߰��մϴ�.
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 470));
        setContentPane(chartPanel);
		

		
		
	}
	
	// ****************************************************************************
	// * JFREECHART DEVELOPER GUIDE *
	// * The JFreeChart Developer Guide, written by David Gilbert, is available *
	// * to purchase from Object Refinery Limited: *
	// * *
	// * http://www.object-refinery.com/jfreechart/guide.html *
	// * *
	// * Sales are used to provide funding for the JFreeChart project - please *
	// * support us so that we can continue developing free software. *
	// ****************************************************************************

	/**
	 * Creates a sample dataset for a Gantt chart.
	 *
	 * @return The dataset.
	 */
	public static IntervalCategoryDataset createDataset(int vNum) {
		final TaskSeries s1 = new TaskSeries("예정 계획");
		final TaskSeriesCollection collection = new TaskSeriesCollection();
		try {
			list = new ArrayList();
			dao = new ProjectDAO();
			list = dao.graphContent(vNum);
			
//			System.out.println(list);
			for(int i = 0; i< list.size(); i++) {
				ArrayList<String> Alist = (ArrayList) list.get(i);
				for(int j = 0; j<Alist.size(); j++) {
					
					System.out.println(Alist.get(j) +" " + j);
					//������
					String namt = Alist.get(0);
					
					// ������¥
					int year = Integer.parseInt((Alist.get(1)).substring(0,4));
					int month = Integer.parseInt((Alist.get(1)).substring(5,7));
					int day = Integer.parseInt((Alist.get(1)).substring(8,10));
					
					int year2 = Integer.parseInt((Alist.get(2)).substring(0,4));
					int month2 = Integer.parseInt((Alist.get(2)).substring(5,7));
					int day2 = Integer.parseInt((Alist.get(2)).substring(8,10));

					System.out.println(year +" " + month +" "+day);
					
					s1.add(new Task(namt,
							new SimpleTimePeriod(date(day, month, year), date(day2, month2, year2))));		// ��, ��+1, ��
				}
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("�ٿ��ȿ͵�" + e.getMessage());
		}
		
		collection.add(s1);
		return collection;


		
	}

	/**
	 * Utility method for creating <code>Date</code> objects.
	 *
	 * @param day   the date.
	 * @param month the month.
	 * @param year  the year.
	 *
	 * @return a date.
	 */
	private static Date date(final int day, final int month, final int year) {

		final Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		final Date result = calendar.getTime();
		return result;

	}

	/**
	 * Creates a chart.
	 * 
	 * @param dataset the dataset.
	 * 
	 * @return The chart.
	 */
	public static JFreeChart createChart(final IntervalCategoryDataset dataset) {
		// �ѱ� ��Ʈ ����
	    Font font = new Font("굴림", Font.PLAIN, 14);
	    GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
	    // ��Ʈ ����
	    ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());
	    ((StandardChartTheme) ChartFactory.getChartTheme()).setExtraLargeFont(font);
	    ((StandardChartTheme) ChartFactory.getChartTheme()).setLargeFont(font);
	    ((StandardChartTheme) ChartFactory.getChartTheme()).setRegularFont(font);
	    ((StandardChartTheme) ChartFactory.getChartTheme()).setSmallFont(font);
        final JFreeChart chart = ChartFactory.createGanttChart(
        		
            "", // chart title
            "공정명", // domain axis label
            "공정날짜", // range axis label
            dataset, // data
            true, // include legend
            true, // tooltips
            false // urls
        );
        return chart;
    }

	/**
	 * Starting point for the demonstration application.
	 *
	 * @param args ignored.
	 */
	public static void main(final String[] args) {

		final GraphView demo = new GraphView(" ", vNum);
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);

	}

}