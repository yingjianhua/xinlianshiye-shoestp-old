/*
Powered shoestp
 */

var lang_obj={
		
		Name_cannot: "El nombre no puede estar vacío",
		The_amount_cannot: "La cantidad no puede estar vacía",
	orders:{
	'status':{
		0:'Pago pendiente',
		1:'Esperando la confirmación del pago',
		2:'Error de pago',
		3:'Esperando la entrega',
		4:'ha sido enviado',
		5:'orden completa',
		6:'Orden cancelada',
		7:'Eliminado',
		'choose_payment':'Elija el método de pago',
		'order_total':'orden total'
		}
	},
	page:{
		prev:'página anterior',
		next:'Página siguiente'
	},

	global:{
		/* 询盘 */
		'already':'Este producto ya tiene una bolsa de consulta',
		'inqadd':'Completamente agregado a su consulta! ',
		'continues':'Continúa caminando',
		'inquery':'Ahora consulta',
		'vcode':'Código de verificación',
		/* 询盘结束 */
		'add':'Agregar',
		'edit':'Editar',
		'sure':'OK',
		'submit':'Enviar',
		'confirm':'confirmar',
		'cancel':'Cancelar',
		'copy_confirm':'Entrar al modo de copia del producto, ¿continuar? ',
		'copy_model_confirm':'Entrar al modo de copia de contenido, ¿continuar? ',
		'del_confirm':'No se puede restaurar después de la eliminación, ¿continuar? ',
		'reset_confirm':'Confirme la configuración predeterminada, ¿continuar? ',
		'used_confirm':'Listo para abrir el modo de procesamiento por lotes, ¿continuar? ',
		'close_confirm':'Listo para desactivar el modo de procesamiento por lotes, ¿continuar? ',
		'my_order_confirm':'¿Listo para abrir el modo ordenar, continuar? ',
		'sold_in_confirm':'¿Listo para abrir el modo de venta, continuar? ',
		'sold_out_confirm':'Listo para cerrar el modelo de ventas, ¿continuar? ',
		'data_posting':'Datos enviados ...',
		'loading':'Cargar. . . ',
		'sync':'Los datos se sincronizan ...',
		'confirm_password_error':'La nueva contraseña no coincide con la contraseña de confirmación, ¡vuelve a ingresar!',
		'del_dat_select':'Seleccione el producto para eliminar',
		'used_dat_select':'Seleccione el producto para comenzar',
		'close_dat_select':'Seleccione el producto para cerrar',
		'dat_select':'Seleccione el producto seleccionado',
		'correct_message':'Complete los datos correctos. ',
		'n_y':['No', 'Sí'],
		'open':'Expandir',
		'pack_up':'Fold',
		'del':'eliminar',
		'picture':'imagen',
		'picture_name':'nombre de la imagen',
		'set_error':'Error de instalación fallida! ',
		'vcode':'Código de verificación',
		'unknown_mistake':'error desconocido'
	},
	format:{
		'mobilephone':'¡Complete el número de teléfono correctamente! ',
		'telephone':'¡Complete el número de teléfono! ',
		'fax':'¡Complete el número de fax correctamente! ',
		'email':'Por favor, complete la dirección de correo electrónico correctamente!',
		'length':'¡La longitud es incorrecta! Debe completar los dígitos %num% . '
		},
	signIn:{
		'title':'Iniciar sesión',
		'error_note':'Dirección de correo electrónico o contraseña incorrecta. Por favor intente de nuevo <BR> Confirme que el bloqueo CAP esté cerrado antes de ingresar la contraseña. ',
		'email':'correo electrónico',
		'password':'contraseña',
		'forgot':'¿Olvidó su <a href="/home/usr_UsrPurchase_sendEmail" class="forgot"> contraseña </a>? ',
		'stay_note':'Siga registrando en <span> para proteger su privacidad: salga cuando haya terminado. </ span>',
		'sign_in':'Iniciar sesión',
		'join_fee':'Gratis para unirse',
		'verification':'Error de código de verificación',
		'original_password_wrong':'La contraseña original es incorrecta',
		'Register_Success':'¡registración exitosa!',
		'Username_Exists':'nombre de usuario ya existe',
		'Old_Password_Empty':'¡La contraseña original no puede estar vacía!',
		'New_Password_Empty':'¡La nueva contraseña no puede estar vacía!',
		'Confirm_Password_Empty':'confirmar la contraseña no puede estar en blanco!',
	},
	newsletter:{
		'success':'¡Agregue suscripción exitosamente! ',
		'exists':'¡Este buzón ya existe para suscribirse! '
	},
	language:{
		'zh-cn':'chino',
		'en':'Inglés',
		'jp':'Japonés',
		'de':'Alemán',
		'fr':'Francés',
		'es':'español',
		'ru':'Ruso',
		'pt':'Portugués',
		'zh_tw':'Chino tradicional',
        'hu':'Hungría'
	},
	manage:{
		frame:{
			'time':'Tiempo',
			'status':'estado',
			'support':['Enterprise QQ', 'Horas de trabajo', 'Número de teléfono', 'WeChat', 'Supervisor', 'Tiempo de caducidad del sitio web'],
			'data_backup':'Copia de seguridad de datos',
			'backup':['base de datos', 'carpeta'],
			'file_upload':'Seleccionar archivo'
		},
		account:{
			'log_in':'El sistema está intentando iniciar sesión, espere ...',
			'log_in_ok':'Inicio de sesión exitoso, salto de página, espere ...',
			'order_quantity':'Orden del sitio web',
			'page_view':'Visita in situ',
			'success_cache':'¡La memoria caché se borró satisfactoriamente! ',
			'percentage':'porcentaje',
			'picture_tips':'¡Ha subido más de XXX imágenes, no puede cargarlas! ',
			'password_tips':'Volver a la contraseña no coincide, por favor, vuelva a ingresar! '
		},
		set:{
			'select_once_language':'Seleccione al menos una versión de idioma',
			'print_0':'Imprimir Declaración de Aduanas',
			'print_1':'Imprimir factura',
			'platform_ary':{
			'SignIn':'Iniciar sesión',
			'Pixel':'Estadísticas'
			},
		},
		module:{
			'sure_module':'¿Tiene que elegir este estilo? ',
			'name':'nombre',
			'url':'dirección de enlace',
			'target':'nueva ventana',
		},
		counrtry:{
			'state':'Provincia',
		},
		photo:{
			'empty_temp':'¡La carpeta temporal se borró correctamente! ',
			'move_success':'Eliminado con éxito',
			'picture_upload':'Carga de imagen'
		},
		shipping:{
			'area_config':'configuración de área',
		},
		products:{
			'category_tips':'Seleccione categoría de producto',
			'qty':'cantidad',
			'price':'precio',
			'mark_up':'aumento de precio',
			'discount':'Oferta',
			'group_attr':'naturaleza combinatoria',
			'wholesale_discount_notes':'El precio del contenido del producto seleccionado por el sitio web actual es el precio unitario, y el cálculo del precio al por mayor se calculará mediante la tubería de descuento. ',
			'prefix_tips':'¡El primer código del número de producto no puede estar vacío! ',
			'sync_change_account':'Convertir cuenta ...'
		},
		sales:{
			'seckill':'Segunda muerte',
			'promotion':'Promoción',
			'proName':'nombre del producto',
			'proPrice':'Precio del producto',
			'proNumber':'Número del producto',
			'proBiref':'Introducción',
			'start_time':'hora de inicio',
			'duration':'Duración',
			'hour':'hora',
			'tuan_price':'Precio de compra del grupo',
			'buyer_count':'Comprar',
			'total_count':'Restringido',
			'promotion_price':'Precio promocional total',
			'seckill_price':'el precio se eleva',
			'qty':'cantidad',
			'remainder_qty':'cantidad residual',
			'max_qty':'Límite de compra',
			'reverseAssociate':'correlación inversa (cuando no hay ninguna combinación que se mostrará simultáneamente a los productos relevantes)',
			'mainArea':'El producto ya existe en el área principal del producto',
			'relatedArea':'El producto ya existe en el área del producto empaquetado',
			'holidayNotice':'la lista de productos Arrastre hacia la derecha de la <b class="fc_red"> "Holiday Modelo Producto región" </ b>',
			'seckillNotice':'la lista de productos de la fricción a la derecha de los <b class="fc_red"> "PRODUCTOS de aislamiento" </ b>',
			'tuanNotice':'Drag lista de productos a la derecha de la <b class="fc_red"> "comprar área de bienes" </ b>',
			'relatedNotice':'la lista de productos de la fricción a la derecha de la <b class="fc_red"> "zona principal producto" </ b> y <b class="fc_red"> "área Bundles" </ b>. ',
			'beyondNumber':'No exceda el número límite máximo:',
			'condition':'Estado de consumo',
			'discount':'Oferta',
			'money':'dinero',
			'condition_tips':' Este cupón se puede utilizar solamente cuando la compra cantidad alcanza el valor establecido, si se establece en 0, no hay restricciones sobre las condiciones de uso. ',
			'discount_tips':'Ingrese un porcentaje de descuento; el 20% equivale al 80% de descuento, el 80% equivale al 20% de descuento. ',
			'money_tips':'Por favor, introduzca el código de cupón entre 8 y 15 dígitos. ',
			"check_choose":"Artículo no verificado"
		},
		email:{
			'load_error':'Falló la lectura de los datos de la plantilla. ¡Inténtalo de nuevo más tarde! ',
			'not_model':'¡No se ha seleccionado ninguna plantilla! ',
			'not_user':'¡Ningún miembro! ',
			'not_content':'¡Sin contenido! '
		},
		mta:{
			'traffic_time':['los últimos 30 días', 'hoy', 'ayer', 'los últimos 7 días', 'los últimos 15 días'],
			'statistics_name':['visitor', 'views']
		}
	},
	products:{
		'warning_number':'Compre menos que o exceda el MOQ del inventario',
		'warning_MOQ':'Cantidad mínima de compra %num% ',
		'warning_Max':'Cantidad máxima de compra %num% ',
		'warning_stock':'Superior al inventario %num% ',
		'warning_attribute':'Seleccione el parámetro',
		'select_country':'- Seleccione su país-',
		'sign_in':'¡Inicia sesión primero! ',
		'free_shipping':'Envío gratis',
		'no_optional':'no opcional',
		'no_up_carriage':'{0} No en el estante',
		'product_deleted':'El producto {0} ha sido eliminado'
	},
	cart:{
		'processing':'Proceso, espere ...',
		'processing_str':'Procesamiento',
		'checked_error':'Seleccione al menos un producto',
		'attribute_error':'Hay un error en la información del producto. Elimine este producto y vuelva a comprarlo. ',
		'address_error':'Por favor ingrese su dirección de envío o elija una de la dirección que ingresó previamente. Para garantizar la información, haga clic en Guardar cuando termine. ',
		'shipping_error':'Seleccione el método de entrega. ',
		'payment_error':'Seleccione un método de pago. ',
		'product_error':'Su carrito de compras está vacío. ',
		'low_error':'Su producto total aún no ha alcanzado el valor mínimo del sitio web',
		'stock_error':'{0} Stock insuficiente, no puede crear un pedido correctamente, ajuste la cantidad de compra.',
		'prod_stock_error':'{0} Inventario insuficiente',
		'no_delivery':'¡Lo siento! ¡No se puede publicar en este país! ',
		'additem_0':'¡Este producto se ha agregado correctamente al carrito! ',
		'additem_1':'<b class="FontColor"> %num%  </ b> elementos en el carrito de compras. Subtotal:',
		'return_shopping':'seguir comprando',
		'proceed_checkout':'Para liquidación',
		'arrival_info_0':'El negocio se ha enviado con éxito y la cobertura del producto se notificará inmediatamente al cliente. ',
		'arrival_info_1':'Inicia sesión para solicitar una notificación de llegada. ',
		'arrival_info_2':'¡Se ha solicitado este producto para llegar! ',
		'use_shipping_address':'Use esta dirección de envío',
		'coupon_title':'Ingrese su código de cupón',
		'coupon_tips':'Código de cupón <strong> %coupon% </ strong> ¡es válido! Tu descuento es ',
		'coupon_code':'Código de cupón',
		'aplicar':'Aplicar',
		'select_y_country':'Elige tu país',
		'remove':'eliminar',
		'batch_remove_select':'¡Seleccione el elemento para eliminar! ',
		'batch_remove_success':'¡La eliminación del producto fue exitosa! ',
		'batch_remove_error':'¡Error en la eliminación del producto! ',
		'continue_str':'Continuar pago',
		'total_amout':'Total',
		'insurance':'Agregue seguro de envío a su pedido',
		'coupon_tips_to':'Código de cupón %coupon% no es válido. O no existe, no se ha activado o ha expirado. ',
		'coupon_tips_th':'Código de cupón <strong> </ strong> no es válido. O no existe, no se ha activado o ha expirado. ',
		'coupon_price_tips':'El importe total del producto es más bajo que las condiciones de consumo del cupón, y el sistema cancela automáticamente los cupones actualmente en uso. ',
		'shipping_method_tips':'¡Por favor, elija un método de envío! ',
		'cart_tips':'1 elemento agregado al automóvil',
		'plz_sel_para':'Seleccione el parámetro',
		'del_confirm':'¿Estás seguro de que quieres eliminarlo? ',
		'paypal_tips':'Seleccione el mismo país que la información de la dirección de la cuenta de PayPal. ',
		'tooLess':'El número de {0} es demasiado pequeño y debe ser mayor que {1}',
		'Delete_Success':'eliminado con éxito',
		'Are_You_Sure':'¿Seguro que quieres eliminar el elemento seleccionado?',
		'FailToRemove':'No se puede borrar',
		'NotOne':'No puede ser menos de 1',
		'ColorAndSize':'Por favor elija color y tamaño',
	},
	seckill:{
		'loading_tips':'No más productos',
		'no_products':'No coincide con ningún producto',
		'end':'Completado',
		'activity_belongs_begin':'{0} pertenece a la actividad a punto de comenzar',
		'activity_belongs_end':'{0} pertenece a la actividad que ha terminado'
	},
	user:{
		'send_email_ture':'Se te envió un correo electrónico con éxito. Por favor, introduzca su dirección de correo electrónico y verifique la dirección de correo electrónico de verificación ',
		'send_email_false':'Disculpa, no se envió el correo debido a un problema del sistema. Por favor intente de nuevo más tarde. ',
		'favorite_success':'¡Agregado con éxito a mis favoritos! ',
		'favorite_saved':'¡Este producto es bien recibido! ',
		'go_to_view':'Ir a ver',
		'order_cancel':'¿Está seguro de que desea eliminar esta orden pendiente? ',
		'sure':'¿Estás seguro? ',
		'delete_shipping':'¿Estás seguro de que deseas eliminar esta dirección? ',
	'reg_error':{
		'PleaseEnter':'Por favor ingrese su %field%.',
		'Email':'Por favor ingrese su dirección de correo electrónico. ',
		'EmailFormat':'El correo electrónico que ingresó no es válido. Por favor, revise su correo electrónico y vuelva a intentarlo. ',
		'PWDConfirm':'Por favor, vuelva a ingresar la nueva contraseña. ',
		'PWDNotMatch':'Su contraseña no coincide, inténtelo de nuevo. ',
		'PWDLENGTH':'La contraseña es de 8-15 dígitos y letras'
		},
	'address_tips':{
		'PleaseEnter':'Por favor ingrese su %field%.',
		'email':'Por favor ingrese su dirección de correo electrónico. ',
		'email_format':'El correo electrónico ingresado no coincide con el valor confirmado del correo electrónico. ',
		'firstname':'Por favor ingrese su nombre. ',
		'firstname_length':'Su nombre debe contener al menos 2 caracteres. ',
		'lastname':'Por favor ingrese su apellido. ',
		'lastname_length':'Su nombre debe contener al menos 2 caracteres. ',
		'address':'Por favor, introduzca la dirección de envío. ',
		'address_length':'La dirección del remitente debe tener al menos 5 caracteres. ',
		'city':'Por favor ingrese su ciudad. ',
		'city_length':'Tu ciudad debe tener al menos 3 caracteres de longitud. ',
		'country':'Seleccione su país de destino. ',
		'state':'Seleccione su estado / provincia / región. ',
		'taxcode':'Lo siento, se requiere su %str% . ',
		'taxcode_length':'Su %str%  debe contener al menos %TaskL% dígitos. ',
		'tariff':'Lo siento, se requiere su %str%  value. ',
		'tariff_length':'Su %str%  debe contener al menos 12 dígitos. ',
		'zip':'Por favor, introduzca el código postal / código postal. ',
		'zip_length':'Su código postal / código postal debe tener al menos 4 dígitos',
		'phone':'Por favor ingrese su número de teléfono. ',
		'phone_format':'Por favor ingrese un número de teléfono válido. ',
		'phone_length':'Su número de teléfono debe tener al menos 7 caracteres. ',
		'Please_add_item':'Por favor agrega el artículo',
		'Input_AMT':'Por favor ingrese el monto del pago',
		}
	},
	'goods_info':{
		'Pleaselogin':'Por favor ingrese',
		'Successfulinclusion':'Inclusión exitosa',
		'product_out':'Este artículo está agotado',
		'Please_purchased':'Por favor ingrese la cantidad comprada',
		'Added_successfully':'Agregado exitosamente',
		'Purchase_success':'Éxito de compra',
		'No_Spec':'Este producto no ha sido publicado.',
		'Successful_Payment':'¡Pago exitoso!',
	},
	
	'my_inquiry_publish':{
		'submitsuccess':'Enviado con éxito',
		'completeinformation':'Por favor complete la información',
		'thecompatibletitle':'El título es demasiado largo, por favor complete el título compatible',
		'thecorrectEmail':'Por favor complete el correo electrónico correcto',
		'thecorrectquantity':'Por favor complete la cantidad correcta',
		'goodsListerr':'Obtener el error de la lista de mercancías',
		'deletethisgoods':'¿Seguro que quieres eliminar estos artículos?',
		'Pleaseselect_a_goods':'Seleccione un elemento o cargue algunas imágenes para que el proveedor sepa lo que quiere',
		'I18N_No':'I18N No'
	},
	'addressfrom':{
		'Inserted_successfully':'Insertado con éxito',
		'Successfully_modified':'Modificado exitosamente',
		'Enter_Query':'Ingrese la consulta',
		'Merchants_Settled':'Los comerciantes se establecieron',
		'Merchant_Side':'Lado del comerciante',
		'Please_Select_The_Shipping_Address':'Por favor seleccione la dirección de envío',
		'Please_Select_The_Billing_Address':'Establezca la dirección de facturación primero.',
		'Submit_Order_Successfully':'Enviar orden con éxito',
		'No_Province':'Los registros provinciales no existen',
		'Has_Residence':'¡Has solicitado la residencia, no puedes volver a presentar la solicitud!',
	},
	'Purchase_Center_Store':{
		'GuangZhou':'Punto de compra de Guangzhou',
		'Spain':'Punto de compra español',
		'Chile':'Punto de compra de Chile',
		'Romania':'Punto de compra rumano',
		'Hungary':'Punto de compra húngaro',
		'America':'Punto de compra',
		'Canada':'Punto de compra canadiense',
		'Russia':'Punto de aprovisionamiento ruso',
		'Belarus':'Punto de compra de Belarus',
		'Ukraine':'Punto de aprovisionamiento de Ucrania',
		'Poland':'Punto de compra polaco',
		'Germany':'Punto de compra alemán',
		'Czekh':'Punto de contratación checo',
		'England':'Punto de compra del Reino Unido',
		'Ireland':'Punto de compra irlandés',
		'Holland':'Punto de compra holandés',
		'France':'Punto de compra francés',
		'Finland':'Punto de compra finlandés',
		'Sweden':'Punto de compra sueco',
		'Norway':'Punto de compra noruego',
		'Denmark':'Punto de compra danés',
		'Korea':'Punto de compra de Corea',
		'Japan':'Punto de compra japonés',
		'Philippines':'Punto de compra en Filipinas',
		'Singapore':'Punto de compra de Singapur',
		'Israel':'Punto de compra de Israel',
		'Turkey':'Punto de compra de Turquía',
		'Australia':'Punto de compra en Australia',
		'New_Zealand':'Punto de compra en Nueva Zelanda',
		'South_Africa':'Punto de compra sudafricano',
	},
	supplier_entry_hint:{
		companyname:"El nombre de la compañía no puede estar vacío! ",
		suppliercategory:"La clasificación del proveedor no puede estar vacía! ",
		companyaddress:"La ubicación de la compañía no puede estar vacía! ",
		city:"La ciudad no puede estar vacía! ",
		companyaddressinfo:"La dirección detallada de la compañía no puede estar vacía! ",
		email:"E-mail no puede estar vacío! ",
		noemail:"¡Formato de correo electrónico ilegal! ",
		fund:" ¡Los fondos de registro no pueden estar vacíos! ",
		code:" ¡El código de crédito social no puede estar vacío! ",
		legalperson:"El nombre corporativo no puede estar vacío! ",
		businesslicense:" ¡La licencia comercial no puede ser válida! ",
		businesslicenseformat:" El período de validez de la licencia comercial es incorrecto. El formato correcto es: 2010/01 / 01",
		entity:"Contact no puede estar vacío! ",
		phone:"¡El número de móvil no puede estar vacío! ",
		accountopeningbank:"El banco de la cuenta de liquidación no puede estar vacío! ",
		bankaccount:"El número de cuenta bancaria no puede estar vacío! ",
		openingbankname:" ¡El nombre de la sucursal bancaria no puede estar vacío! ",
		openingbankaddress:" ¡La ubicación de la sucursal bancaria no puede estar vacía! ",
		Legalperson_idnumber_passportnumber:"El número de tarjeta de identidad / pasaporte corporativo no puede estar vacío! ",
		Operationdirector_idnumber_passportnumber:"La persona a cargo de la identificación / número de pasaporte no puede estar vacía! ",
		Enterprisecertificate:" ¡Necesito cargar las credenciales comerciales! ",
		legalperson_photo_front:"¡Necesita cargar una tarjeta de identificación / pasaporte legal! ",
		legalperson_photo_reverseside:"Necesita cargar una tarjeta de identificación legal / pasaporte foto inversa! ",
		Operationdirecto_photo_front:"Requiere la carga de la persona a cargo de la operación ID / pasaporte frente! ",
		Operationdirecto_reverseside:" necesita cargar la operación responsable persona ID / pasaporte inversa! "
	},
	mobile:{
		Size:'tamaño:',
		Color:'Color:',
		Order_Number:'Número de orden.',
		Double:'Doble',
		Total_Price:'Precio total:',
		Shop:'Tienda:',
		No_Data:'Sin datos',
		Collection:'Colección',
		Add_To_Shopping_Cart:'Agregar al carrito de compras',
		Whole:'Todo',
		Collection1:'Exitosa colección',
		Collection2:'Cancelar colección',
		Collection3:'Colección fallida',
		Go_To_Set:'Ir a establecer',
		Set_Later:'Establecer más adelante',
		Tips:'Consejos',
		Out_Of_Stock:'Agotado',
		Inventory_Shortage:'Escasez de inventario, por favor rellene',
		
		Confirm_receipt:"Confirmar recibo?",
		Successful_receipt:"Recibido con éxito",
		First_page:"Ya es la primera página",
		The_last_page:"Es la última página",
		Adquisition_failed: "Get failed",
		Failed_to_send: "Enviar fallido",
		Successful_recovery: "La recuperación es exitosa",
		all_reviews: "Todos los comentarios",
	},
	cart3:{
		name:'Nombre',
		Contents:'Observaciones',
		SentMoney:'Remesas',
		Number:'Número de referencia',
		Currency:'moneda',
	},
	home:{
		brneficiary:'Beneficiario',
		bankaddress:'Dirección bancaria',
		swiftcode:'Código SWIFT',
		beneficiarybank:'Banco beneficioso',
		beneficiaryaccount:'Cuenta del beneficiario',
		depos_it_bank_subbranch_ascription:'Titular de cuenta de liquidación',
		account_name:'Cuenta bancaria',
		depos_it_bank_subbranch:'Nombre de la sucursal',
		bank_account:'Ubicación de la sucursal bancaria',
	},
	supplier:{
		products:'Producto',
		productsStyle:'Método de procesamiento'
	}
};