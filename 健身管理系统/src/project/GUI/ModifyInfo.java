package project.GUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ModifyInfo {
	// 读入文件
	public String readFileContent(String filePath, String sourceStr, String modifyStr) {
		BufferedReader br = null;
		String info = null;
		StringBuffer writeALL = new StringBuffer();		// 保存修改过后的所有内容
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
			while ((info = br.readLine()) != null) {
				StringBuffer modify = new StringBuffer();
				String condition = InfoGUI.NAME;
				if (info.startsWith(condition)) {
					modify.append(info);
					int indexOf = info.indexOf(sourceStr);
					modify.replace(indexOf, indexOf + sourceStr.length(), modifyStr);
					modify.append(System.getProperty("line.separator"));		// 添加换行
					writeALL.append(modify);
				} else {
					modify.append(info);
					modify.append(System.getProperty("line.separator"));
					writeALL.append(modify);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
					br = null;
				}
			}
		}
		return writeALL.toString();
	}

	// 写入文件
	public boolean writeFile(String filePath, String content) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"));
			bw.write(content);
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					bw = null;
				}
			}
		}
		return true;
	}

}