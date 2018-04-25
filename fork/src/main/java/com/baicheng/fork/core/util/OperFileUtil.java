package com.baicheng.fork.core.util;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class OperFileUtil {

	protected final static Log log = LogFactory.getLog(OperFileUtil.class);

	/**
	 * 得到项目跟目录下面的一个文件夹的绝对路径 /E:/MyWork/htsw/nw/.metadata/.plugins/org.eclipse.wst
	 * .server.core/tmp1/wtpwebapps/htswback
	 *
	 * @param fileName 项目中的文件夹名称
	 * @return
	 */
	public static String calculateProFilePath(String folder) throws Exception {
		String rootPath = getRootPath();
		System.out.println(rootPath);
		return rootPath + folder;
	}

	/**
	 * java工程得到根目录class
	 *
	 * @param folder
	 * @return
	 */
	public static String getClassProFilePath(String folder) {
		String rootPath = System.getProperty("user.dir");
		System.out.println(rootPath);
		return rootPath + folder;
	}

	/**
	 * 在web项目中得到项目的绝对路径
	 *
	 * @return
	 */
	public static String getRootPath() throws Exception {
		String rootPath = OperFileUtil.class.getClassLoader().getResource("/")
				.getPath();
		rootPath = URLDecoder.decode(rootPath, "utf-8");
		int end = rootPath.indexOf("WEB-INF");
		rootPath = rootPath.substring(0, end - 1);
		return rootPath;
	}

	/**
	 * 得到web项目的类的绝对路径
	 *
	 * @return
	 */
	public static String getProClassPath() {
		String rootPath = OperFileUtil.class.getClassLoader().getResource("/")
				.getPath();
		return rootPath;
	}

	/**
	 *
	 */
	/**
	 * 获取文件 可以根据正则表达式查找
	 *
	 * @param dir String 文件夹名称
	 * @param s String 查找文件名，可带*.?进行模糊查询
	 * @param extension 是否通过文件扩展名查找
	 * @return File[] 找到的文件
	 */
	public static File[] getFiles(String dir, String s, boolean extension) {
		// 开始的文件夹
		File file = new File(dir);
		ArrayList<File> list;
		s = s.replace('.', '#');
		s = s.replaceAll("#", "\\\\.");
		s = s.replace('*', '#');
		s = s.replaceAll("#", ".*");
		s = s.replace('?', '#');
		s = s.replaceAll("#", ".?");
		s = "^" + s + "$";

		Pattern p = Pattern.compile(s);
		if (extension) {
			list = filePatternExe(file, p);
		} else {
			list = filePattern(file, p);
		}
		File[] rtn = new File[list.size()];
		list.toArray(rtn);
		return rtn;
	}

	/**
	 *
	 */
	/**
	 * @param file File 起始文件夹
	 * @param p Pattern 匹配类型
	 * @return ArrayList 其文件夹下的文件夹
	 */
	private static ArrayList<File> filePattern(File file, Pattern p) {
		if (file == null) {
			return null;
		} else if (file.isFile()) {
			Matcher fMatcher = p.matcher(file.getName());
			if (fMatcher.matches()) {
				ArrayList<File> list = new ArrayList<File>();
				list.add(file);
				return list;
			}
		} else if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files != null && files.length > 0) {
				ArrayList<File> list = new ArrayList<File>();
				for (int i = 0; i < files.length; i++) {
					ArrayList<File> rlist = filePattern(files[i], p);
					if (rlist != null) {
						list.addAll(rlist);
					}
				}
				return list;
			}
		}
		return null;
	}

	/**
	 *
	 */
	/**
	 * @param file File 起始文件夹
	 * @param p Pattern 匹配类型
	 * @return ArrayList 其文件夹下的文件夹
	 */
	private static ArrayList<File> filePatternExe(File file, Pattern p) {
		if (file == null) {
			return null;
		} else if (file.isFile()) {
			String ex = FilenameUtils.getExtension(file.getName());
			Matcher fMatcher = p.matcher(ex);
			if (fMatcher.matches()) {
				ArrayList<File> list = new ArrayList<File>();
				list.add(file);
				return list;
			}
		} else if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files != null && files.length > 0) {
				ArrayList<File> list = new ArrayList<File>();
				for (int i = 0; i < files.length; i++) {
					ArrayList<File> rlist = filePatternExe(files[i], p);
					if (rlist != null) {
						list.addAll(rlist);
					}
				}
				return list;
			}
		}
		return null;
	}

	/**
	 * 将文件复制到目标文件夹（如果目标文件夹不存在，则自动创建）
	 *
	 * @param oldImgFolder目标文件夹
	 * @param newImgFile文件
	 */
	public static void copyFileToDirectory(File copyFile, File deskDir) {
		try {
			boolean isExist = copyFile.exists();
			if (isExist) {
				deleteFile(copyFile, deskDir);
				FileUtils.forceMkdir(deskDir);
				FileUtils.copyFileToDirectory(copyFile, deskDir);
			} else {
				log.info(copyFile.getCanonicalPath() + "图片不存在");
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(deskDir.getAbsolutePath()
					+ "==================复制图片错误========================");
		}
	}

	/**
	 * deskDir目标文件夹中有srcFile的话就删除
	 *
	 * @param srcFile
	 * @param deskDir
	 * @throws IOException
	 */
	private static void deleteFile(File srcFile, File deskDir)
			throws IOException {
		String deskDirPath = deskDir.getCanonicalPath();
		String fileName = srcFile.getName();
		String deskFile = deskDirPath + File.separator + fileName;
		FileUtils.forceDeleteOnExit(new File(deskFile));
	}

	/**
	 * 将文件移动到目标文件夹（如果目标文件夹不存在，则自动创建[目标文件下面有个文件则删除]）
	 *
	 * @param oldImgFolder目标文件夹
	 * @param newImgFile文件
	 */
	public static void moveFileToDirectory(File deskDir, File moveFile) {
		try {
			if (moveFile.exists()) {
				deleteFile(moveFile, deskDir);
				FileUtils.copyFileToDirectory(moveFile, deskDir, false);
			} else {
				log.info("图片没有了" + moveFile.getCanonicalPath());
			}
		} catch (IOException e) {
			log.error("==================复制图片错误========================");
			e.printStackTrace();
		}
	}

	/**
	 * 判断文件是否在指定文件夹下面存在,如果存在返回这个文件否则返回空
	 *
	 * @param deskFilePath 文件夹
	 * @param fileName 文件
	 * @return
	 */
	public static File fileIsExit(String deskFilePath, String fileName) {
		File file = new File(deskFilePath + File.separator + fileName);
		boolean flag = file.exists();
		if (flag) {
			return file;
		} else {
			return null;
		}
	}

	/**
	 * 修改文件名
	 *
	 * @param fileName
	 * @param num
	 * @return
	 */
	public static String renameFile(String prefix, String fileName) {
		String t_ext = fileName.substring(fileName.lastIndexOf(".") + 1);
		String file_name = prefix + "." + t_ext;
		return file_name;
	}

	public static void main(String[] args) {
		try {
			System.out.println(OperFileUtil.getRootPath());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
