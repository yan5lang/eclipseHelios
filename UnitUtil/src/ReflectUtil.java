

/**
 * The class used...
 *
 * @author: cyan
 */
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 反射工具类
 *
 */
public class ReflectUtil {
	/**
	 * 按照类名反射出它的一个对象
	 * @param classname
	 * @return Object
	 */
	@SuppressWarnings("rawtypes")
	public static Object getObjByClassName(String classname) {
		Object obj = null;
		if (classname != null) {
			try {
				Class a = Class.forName(classname);
				obj = a.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

	/**
	 * 按照类名,参数值反射出它的一个对象
	 * @param classname
	 * @param parameter
	 * @return Object
	 */
	@SuppressWarnings( { "unchecked", "rawtypes" })
	public static Object getObjByClassNameAndParameter(String classname,
			Object[] parameter) {
		Object obj = null;
		if (classname != null) {
			try {
				Class a = Class.forName(classname);
				// 获取公有的构造函数,指定参数
				Constructor con = a
						.getConstructor(getParameterClass(parameter));
				obj = con.newInstance(parameter);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

	/**
	 * 用类名反射调用它的某个方法(一般针对工具类或者service)(无参数)
	 * @param className
	 * @param methodName
	 * @return Object
	 */
	public static Object invokeMethod(String className, String methodName) {
		return invokeMethodWithObjHasParame(className,
				getObjByClassName(className), methodName, new Object[0]);
	}

	/**
	 * 用类名反射调用它的某个方法(一般针对工具类或者service)(有参数)
	 * @param className
	 * @param methodName
	 * @param parameter
	 * @return Object
	 */
	public static Object invokeMethodHasParame(String className,
			String methodName, Object[] parameter) {
		return invokeMethodWithObjHasParame(className,
				getObjByClassName(className), methodName, parameter);
	}

	/**
	 * 用对象反射调用它的某个方法(没有参数的方法)
	 * @param className
	 * @param obj
	 * @param methodName
	 * @return
	 */
	public static Object invokeMethodWithObj(String className, Object obj,
			String methodName) {
		return invokeMethodWithObjHasParame(className, obj, methodName,
				new Object[0]);
	}

	/**
	 * 用对象反射调用它的某个方法(有参数的方法)
	 * @param className
	 * @param obj
	 * @param methodName
	 * @param parameter
	 * @return Object
	 */
	public static Object invokeMethodWithObjHasParame(String className,
			Object obj, String methodName, Object[] parameter) {
		return invokeMethodWithObjHasSpecialParame(className, obj, methodName,
				parameter, getParameterClass(parameter));
	}

	/**
	 * 获取参数列表的class对象
	 * @param parameter
	 * @return Class[]
	 */
	@SuppressWarnings("rawtypes")
	private static Class[] getParameterClass(Object[] parameter) {
		Class[] methodParameters = null;
		if (parameter != null && parameter.length > 0) {
			methodParameters = new Class[parameter.length];
			for (int i = 0; i < parameter.length; i++) {
				methodParameters[i] = parameter[i].getClass();
			}
		}
		return methodParameters;
	}

	/**
	 * 用对象反射调用它的某个方法(指定参数类型的方法)
	 * @param className
	 * @param obj
	 * @param methodName
	 * @param parameter
	 * @param methodParameters
	 * @return Object
	 */
	@SuppressWarnings("rawtypes")
	public static Object invokeMethodWithObjHasSpecialParame(String className,
			Object obj, String methodName, Object[] parameter,
			Class[] methodParameters) {
		Object object = null;
		try {
			Method method = Class.forName(className).getMethod(
					methodName.trim(), methodParameters);
			object = method.invoke(obj, parameter);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return object;
	}

	/**
	 * 反射获取一个类的方法信息 包括参数,方法名,返回类型
	 * @param className
	 * @return List<String>
	 */
	@SuppressWarnings("rawtypes")
	public static List<String> getMethodMsg(String className) {
		List<String> retValue = new ArrayList<String>();
		try {
			// 通过getMethods得到类中包含的方法
			Class myClass = Class.forName(className);
			Method m[] = myClass.getDeclaredMethods();
			for (int i = 0; i < m.length; i++) {
				String meth = m[i].toString();
				// 截取出所有的参数,参数以,形式分割
				meth = meth.substring(meth.indexOf("(") + 1, meth.indexOf(")"));
				// ret由3部分构成：参数;方法名;返回类型
				String ret = meth + ";" + m[i].getName() + ";"
						+ m[i].getReturnType();
				retValue.add(ret);
			}
			return retValue;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return retValue;
	}
	// http://zhidao.baidu.com/question/151090808.html
	// public static void main(String args[]) {
	// getMe();
	// }
	//
	// public static void getMethod() {
	// try {
	// Class cls = Class.forName("com.epoint.utility.xml.ReadXML");
	// Method methlist[] = cls.getDeclaredMethods();
	//
	// for (int i = 0; i < methlist.length; i++) {
	// Method m = methlist[i];
	// System.out.println("name= " + m.getName());
	// Class pvec[] = m.getParameterTypes();
	// for (int j = 0; j < pvec.length; j++) {
	// System.out.println("param #" + j + " " + pvec[j]);
	// }
	// Class evec[] = m.getExceptionTypes();
	// for (int j = 0; j < evec.length; j++) {
	// System.out.println("exc #" + j + " " + evec[j]);
	// }
	// System.out.println("return type = " + m.getReturnType());
	// System.out.println("-----");
	// }
	// }
	//
	// catch (Throwable e) {
	//
	// System.err.println(e);
	//
	// }
	//
	// }
	//
	// public static void getMe() {
	// try {
	// Class cls = Class.forName("com.epoint.utility.xml.ReadXML");
	// Constructor ctorlist[] = cls.getDeclaredConstructors();
	// for (int i = 0; i < ctorlist.length; i++) {
	// Constructor ct = ctorlist[i];
	// System.out.println("name = " + ct.getName());
	// Class pvec[] = ct.getParameterTypes();
	// for (int j = 0; j < pvec.length; j++) {
	// System.out.println("param #" + j + " " + pvec[j]);
	// }
	// Class evec[] = ct.getExceptionTypes();
	// for (int j = 0; j < evec.length; j++) {
	// System.out.println("exc #" + j + " " + evec[j]);
	// }
	// System.out.println("-----");
	// }
	// }
	// catch (Throwable e) {
	// System.err.println(e);
	// }
	//
	// }
}