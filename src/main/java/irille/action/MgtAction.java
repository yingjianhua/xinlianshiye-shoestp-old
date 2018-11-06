package irille.action;

import irille.pub.bean.BeanMain;
import irille.pub.util.upload.ImageUpload;
import irille.view.plt.ImageView;

import java.io.File;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;



public abstract class MgtAction<THIS extends BeanMain<?, ?>> extends ActionBase<THIS> {

	private static final long serialVersionUID = 8880466758842325745L;
	
	public THIS getBean() {
		return _bean;
	}

	public void setBean(THIS bean) {
		this._bean = bean;
	}
	
//---------------------------------------------上传文件功能--------------------------------------	
	private String fileFileName = "";
	private File file;
	
	public void upload() throws Exception {
		if(getLoginSys() == null) {
			JSONObject json = new JSONObject();
			json.put("success", false);
			json.put("msg", "登录超时,请重新登录");
			writerOrExport(json);
		} else {
			ImageView view = ImageUpload.upload(beanClazz(), fileFileName, file);
			JSONObject json = new JSONObject();
			json.put("ret", 1);
			json.put("result", new JSONObject(new ObjectMapper().writeValueAsString(view)));
			json.put("success", true);
			writerOrExport(json);
		}
	}
	
	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
//---------------------------------------------上传文件功能--------------------------------------

}
