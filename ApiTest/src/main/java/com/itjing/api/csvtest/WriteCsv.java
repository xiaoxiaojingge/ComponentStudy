package com.itjing.api.csvtest;

import org.apache.commons.lang3.time.DurationFormatUtils;

public class WriteCsv {

	// public static void main(String[] args) throws IllegalAccessException,
	// NoSuchMethodException, InvocationTargetException {
	// CsvWriterSettings csvWriterSettings = new CsvWriterSettings();
	//
	// File file = new File("d:/test/" + System.currentTimeMillis() + ".csv");
	// CsvWriter csvWriter = new CsvWriter(file,csvWriterSettings);
	// List<WriteBean> writeBeans = new ArrayList<>();
	// WriteBean writeBean = new WriteBean(1567, "abc", UUID.randomUUID().toString(), new
	// Date(), 5);
	// writeBeans.add(writeBean);
	// writeBeans.add(new WriteBean(1982,"def",UUID.randomUUID().toString(),new
	// Date(),10));
	//
	// for (WriteBean beans : writeBeans) {
	// Map<String, String> describe = BeanUtils.describe(beans);
	// csvWriter.writeRow(describe);
	//// csvWriter.writeValuesToRow();
	//// csvWriter.writeRow(describe.values());
	// }
	// csvWriter.flush();
	//
	// csvWriter.close();
	// System.out.println(file);
	// }
	//
	public static void main(String[] args) {
		System.out.println(DurationFormatUtils.formatDuration(3232L * 1000, "HH:mm:ss"));
	}

}
