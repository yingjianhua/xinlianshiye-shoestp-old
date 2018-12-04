/*
Powered shoestp
 */

var lang_obj={
		
		Name_cannot: "Le nom ne peut pas être vide",
		The_amount_cannot: "Le montant ne peut être vide",
	orders:{
		'status':{
			0:'Paiement en attente',
			1:'En attente de confirmation de paiement',
			2:'Erreur de paiement',
			3:'En attente de livraison',
			4:'A expédié',
			5:'Remplissez la commande',
			6:'commande annulée',
			7:'Supprimé',
			'choose_payment':'Choisissez le mode de paiement',
			'order_total':'Total order'
			}
	},
	page:{
		prev:'page précédente',
		next:'Page suivante'
	},

	global:{
		/* 询盘 */
	'already':'Ce produit a déjà un sac dinterrogation',
	'inqadd':'Ajouté avec succès à votre demande!',
	'continues':'Continuer à se promener',
	'inquery':'Enquête maintenant',
	'vcode':'Code de vérification',
		/* 询盘结束 */
	'add':'Ajouter',
	'edit':'Modifier',
	'sure':'OK',
	'submit':'Soumettre',
	'confirm':'confirmer',
	'cancel':'Annuler',
	'copy_confirm':'Entrer dans le mode de copie du produit, continuez? ',
	'copy_model_confirm':'Entrer en mode copie de contenu, continuer? ',
	'del_confirm':'Ne peut pas être restauré après la suppression, continuez? ',
	'reset_confirm':'Confirmez les paramètres par défaut, continuez? ',
	'used_confirm':'Prêt à ouvrir le mode de traitement par lots, continuez? ',
	'close_confirm':'Prêt à désactiver le mode de traitement par lots, continuez? ',
	'my_order_confirm':'Prêt à ouvrir le mode de tri, continuez? ',
	'sold_in_confirm':'Prêt à ouvrir le mode de vente, continuez? ',
	'sold_out_confirm':'Prêt à fermer le modèle de vente, continuez? ',
	'data_posting':'Données soumises ...',
	'loading':'Charge. . . ',
	'sync':'Données en cours de synchronisation ...',
	'confirm_password_error':'Le nouveau mot de passe ne correspond pas au mot de passe de confirmation, veuillez le saisir à nouveau!',
	'del_dat_select':'Veuillez sélectionner le produit à supprimer',
	'used_dat_select':'Veuillez sélectionner le produit à démarrer',
	'close_dat_select':'Veuillez sélectionner le produit à fermer',
	'dat_select':'Veuillez sélectionner le produit sélectionné',
	'correct_message':'Veuillez renseigner les données correctes. ',
	'n_y':['Non', 'Oui'],
	'open':'Expand',
	'pack_up':'Fold',
	'del':'supprimer',
	'picture':'image',
	'picture_name':'nom de limage',
	'set_error':'Installation échouée erreur inconnue! ',
	'vcode':'Code de vérification',
	'unknown_mistake':'erreur inconnue'
	},
	format:{
		'mobilephone':'Sil vous plaît, remplissez correctement le numéro de téléphone! ',
		'telephone':'Sil vous plaît, entrez le numéro de téléphone! ',
		'fax':'Sil vous plaît, remplissez correctement le numéro de fax! ',
		'email':'Sil vous plaît, remplissez correctement ladresse e-mail! ',
		'length':'La longueur est incorrecte! Vous devez renseigner les chiffres %num%. '
	},
	signIn:{
		'title':'Login',
		'error_note':'Adresse e-mail ou mot de passe incorrect. Veuillez réessayer <BR> Veuillez confirmer que le verrou CAP est fermé avant d’entrer le mot de passe. ',
		'email':'email',
		'password':'mot de passe',
		'forgot':'Vous avez oublié votre <a href="/home/usr_UsrPurchase_sendEmail" class="forgot"> mot de passe </a>? ',
		'stay_note':'Continuez à vous connecter <span> pour protéger votre vie privée - quittez lorsque vous avez terminé. </ span>',
		'sign_in':'Connexion',
		'join_fee':'Libre de rejoindre',
		'verification':'Erreur de code de vérification',
		'original_password_wrong':"Le mot de passe d'origine est erroné",
		'Register_Success':'succès de l&#39;inscription!',
		'Username_Exists':'Ce nom d&#39;utilisateur existe déjà',
		'Old_Password_Empty':'Le mot de passe original ne peut être vide!',
		'New_Password_Empty':'Le nouveau mot de passe ne peut pas être vide!',
		'Confirm_Password_Empty':'confirmez que le mot de passe ne peut pas être vide!',
	},
	newsletter:{
		'success':'Ajoutez labonnement avec succès! ',
		'exists':'Cette boîte aux lettres existe déjà pour abonnement! '
	},
	language:{
		'zh-cn':'Chinois',
		'en':'anglais',
		'jp':'japonais',
		'de':'allemand',
		'fr':'français',
		'es':'espagnol',
		'ru':'russe',
		'pt':'Portugais',
		'zh_tw':'Chinois traditionnel',
        'hu':'Hun'
	},
	manage:{
		frame:{
			'time':'Time',
			'status':'état',
			'support':['Enterprise QQ', 'Heures de travail', 'Numéro de téléphone', 'WeChat', 'Superviseur', 'Heure dexpiration du site'],
			'data_backup':'sauvegarde des données',
			'backup':['database', 'folder'],
			'file_upload':'Sélectionner le fichier'
		},
		account:{
		'log_in':'Le système tente de se connecter, veuillez patienter ...',
		'log_in_ok':'Connexion réussie, saut de page, veuillez patienter ...',
		'order_quantity':'Commande du site',
		'page_view':'visite sur site',
		'success_cache':'Le cache a été effacé avec succès!',
		'pourcentage':'pourcentage',
		'picture_tips':'Vous avez téléchargé plus de XXX images, vous ne pouvez pas les télécharger!',
		'password_tips':'Le re-mot de passe ne correspond pas, veuillez ré-entrer!'
		},
		set:{
		'select_once_language':'Veuillez sélectionner au moins une version linguistique',
		'print_0':'Impression dune déclaration en douane',
		'print_1':'imprimer la facture',
		'platform_ary':{
				'SignIn':'Connexion',
				'Pixel':'Statistiques'
			},
		},
		module:{
			'sure_module ':' Faut-il choisir ce style? ',
			'name ':' nom ',
			'url ':' adresse du lien ',
			'target ':' nouvelle fenêtre ',
		},
		counrtry:{
			'state ':' Province ',
		},
		photo:{
			'empty_temp ':' Le dossier temporaire a été effacé avec succès! ',
			'move_success ':' Supprimé avec succès ',
			'picture_upload ':' Image Upload '
		},
		shipping:{
			'area_config ':' Configuration régionale ',
		},
		products:{
			'category_tips ':' Veuillez sélectionner une catégorie de produit ',
			'qty ':' quantité ',
			'price ':' prix ',
			'mark_up ':' Les prix augmentent ',
			'discount ':' Offre ',
			'group_attr ':' nature combinatoire ',
			'wholesale_discount_notes ':' Le prix du contenu du produit sélectionné par le site Web actuel est le prix unitaire et le calcul du prix de gros sera calculé par le pipeline de remises. ',
			'prefix_tips ':' Le premier numéro de produit ne peut pas être vide! ',
			'sync_change_account ':' Convertir un compte ... '
		},
		sales:{
			'seckill':'Spike',
			'promotion':'Promotion',
			'proName':'Nom du produit',
			'proPrice':'Prix du produit',
			'proNumber':'Numéro de produit',
			'proBiref':'Introduction',
			'start_time':'Heure de démarrage',
			'duration':'Durée',
			'hour':'heures',
			'tuan_price':'prix dachat groupé',
			'buyer_count':'Achats',
			'total_count':'restreint',
			'promotion_price':'Prix promotionnel total',
			'seckill_price':'Le prix monte en flèche',
			'qty':'quantité',
			'remainder_qty':'quantité résiduelle',
			'max_qty':'Limite dachat',
			'reverseAssociate':'Corrélation inverse (lorsqu"il n"y a pas de combinaison, la combinaison sera affichée pour les produits associés en même temps)',
			'mainArea':'Le produit existe déjà dans la zone de produits principale',
			'relatedArea':'Le produit existe déjà dans la zone de produits groupée',
			'holidayNotice':'Faites glisser la liste de produits située à droite de la zone de produit <b class =" fc_red ">" Modèle de vacances "</ b>',
			'seckillNotice':'Faites glisser la liste de produits à droite dans la <b class =" fc_red ">" zone de produit isolée "</ b>',
			'tuanNotice':'Faites glisser la liste de produits située à droite dans la <b class =" fc_red ">" Zone de produits du groupe "</ b>',
			'relatedNotice':'Faites glisser la liste de produits à droite dans la <b class =" fc_red ">" Zone de produit principale "</ b> et <b class =" fc_red ">" Zone de produit groupée "</ b>. ',
			'beyondNumber':'Ne pas dépasser le nombre limite maximum:',
			'condition':'Statut de consommation',
			'discount':'Offre',
			'money':'Money',
			'condition_tips':'Ce coupon ne peut être utilisé que lorsque le montant de lachat atteint la valeur définie; si 0 est défini, les conditions dutilisation ne sont pas limitées.',
			'discount_tips':'Veuillez saisir un pourcentage de remise; 20% correspond à 80% de réduction, 80% à 20% de remise.',
			'money_tips':'Veuillez entrer le code coupon entre 8 et 15 chiffres.',
			"check_choose":"Élément non vérifié"
		},
		email:{
			'load_error':'La lecture des données du modèle a échoué, veuillez réessayer plus tard!',
			'not_model':'Aucun template sélectionné!',
			'not_user':'Aucun membre!',
			'not_content':'Pas de contenu!'
		},
		mta:{
			'traffic_time':["30 derniers jours", "Aujourd'hui", "Hier", "7 derniers jours", "15 derniers jours"],
			'statistics_name':['visitor', 'views']
		}
	},
	products:{
		'warning_number':'Acheter moins ou plus que le MOQ dinventaire',
		'warning_MOQ':'Quantité dachat minimum %num%',
		'warning_Max':'Quantité dachat maximale %num%',
		'warning_stock':'supérieur au stock %num%',
		'warning_attribute':'Veuillez sélectionner un paramètre',
		'select_country':'--Sélectionnez votre pays-',
		'sign_in':'Veuillez vous connecter dabord!',
		'free_shipping':'Livraison gratuite',
		'no_optional':'non facultatif',
		'no_up_carriage':'{0} Pas sur l&#39;étagère',
		'product_deleted':'Le produit {0} a été supprimé'
	},
	cart:{
		'processing':'Traitement, veuillez patienter ...',
		'processing_str':'Traitement',
		'checked_error':'Veuillez sélectionner au moins un produit',
		'attribute_error':'Il y a une erreur dans les informations du produit, veuillez supprimer ce produit et le racheter.',
		'address_error':'Veuillez saisir votre adresse de livraison ou en sélectionner une à ladresse que vous avez saisie précédemment. Pour vous assurer que les informations sont correctes, cliquez sur Enregistrer lorsque vous avez terminé.',
		'shipping_error':'Veuillez sélectionner le mode de livraison.',
		'payment_error':'Veuillez sélectionner un mode de paiement.',
		'product_error':'Votre panier est vide.',
		'low_error':'Votre produit total na pas encore atteint la valeur de consommation minimale du site Web',
		'stock_error':'{0} Stock insuffisant, impossible de créer une commande correctement, ajustez la quantité achetée.',
		'prod_stock_error':'{0} Inventaire insuffisant',
		'no_delivery':'Désolé! Impossible de livrer dans ce pays!',
		'additem_0':'Ce produit a été ajouté au panier avec succès!',
		'additem_1':'<b class =" FontColor "> %num% </ b> éléments du panier. Sous-total:',
		'return_shopping':'Continuer vos achats',
		'proceed_checkout':'Pour règlement',
		'arrival_info_0':'Lentreprise a été soumise avec succès et la couverture du produit sera immédiatement notifiée au client.',
		'arrival_info_1':'Veuillez vous identifier pour demander un avis darrivée.',
		'arrival_info_2':'Ce produit a été demandé pour arriver!',
		'use_shipping_address':'Utiliser cette adresse de livraison',
		'coupon_title':'Entrez votre code promo',
		'coupon_tips':'Le code de coupon" <strong> %coupon%  </ strong> "est valide! Votre remise est',
		'coupon_code':'code promo',
		'apply':'Appliquer',
		'select_y_country':'Choisissez votre pays',
		'remove':'Supprimer',
		'batch_remove_select':'Veuillez sélectionner lélément à supprimer!',
		'batch_remove_success':'Le retrait du produit a réussi!',
		'batch_remove_error':'Le retrait du produit a échoué!',
		'continue_str':'Continuer la caisse',
		'total_amout':'Montant total',
		'assurance':'Ajouter une assurance dexpédition à votre commande',
		'coupon_tips_to':'Le code coupon %coupon%  est invalide. Il n’existe pas ou n’a pas été activé ou a expiré.',
		'coupon_tips_th':'Le code de coupon <strong> </ strong> n’est pas valide. Il n’existe pas ou n’a pas été activé ou a expiré.',
		'coupon_price_tips':'Le montant total du produit est inférieur aux conditions de consommation du coupon et le système annule automatiquement les coupons actuellement utilisés.',
		'shipping_method_tips':'Veuillez choisir un mode de livraison!',
		'cart_tips':'1 article ajouté à la voiture',
		'plz_sel_para':'Veuillez sélectionner les paramètres',
		'del_confirm':'Etes-vous sûr de vouloir le supprimer?',
		'paypal_tips':'Veuillez sélectionner le même pays que ladresse du compte PayPal.',
		'tooLess':'Le nombre de {0} est trop petit et doit être supérieur à {1}',
		'Delete_Success':'supprimé avec succès',
		'Are_You_Sure':"Êtes-vous sûr de vouloir supprimer l'élément sélectionné?",
		'FailToRemove':'Impossible de supprimer',
		'NotOne':'Ne peut être inférieur à 1',
		'ColorAndSize':"S'il vous plaît choisir la couleur et la taille",
	},
	seckill:{
		'loading_tips':'Plus de produits',
		'no_products':'ne correspond à aucun produit',
		'end':'Terminé',
		'activity_belongs_begin':'{0} appartient à l&#39;activité sur le point de commencer',
		'activity_belongs_end':'{0} appartient à l&#39;activité a pris fin'
	},
	user:{
		'send_email_ture':'Un e-mail vous a été envoyé avec succès. Veuillez entrer votre adresse e-mail et vérifier ladresse e-mail de vérification',
		'send_email_false':'Désolé, le courrier na pas pu être envoyé en raison dun problème système. Veuillez réessayer plus tard.',
		'favorite_success':'ajouté avec succès à mes favoris!',
		'favorite_saved':'Ce produit est bien reçu!',
		'go_to_view':'Aller à la vue',
		'order_cancel':'Etes-vous sûr de vouloir supprimer cet ordre en attente?',
		'sure':'Êtes-vous sûr?',
		'delete_shipping':'Etes-vous sûr de vouloir supprimer cette adresse?',
	'reg_error':{
		'PleaseEnter':'Veuillez entrer votre %field%.',
		'Email':'Veuillez entrer votre adresse e-mail.',
		'EmailFormat':'Lemail que vous avez entré nest pas valide. Veuillez vérifier votre e-mail et réessayer.',
		'PWDConfirm':'Veuillez ressaisir le nouveau mot de passe.',
		'PWDNotMatch':'Votre mot de passe ne correspond pas, veuillez réessayer.',
		'PWDLENGTH':'Le mot de passe est de 8 à 15 chiffres et lettres'
		},
	'address_tips':{
		'PleaseEnter':'Veuillez entrer votre %field%.',
		'email':'Veuillez entrer votre adresse e-mail.',
		'email_format':'Le courrier électronique saisi ne correspond pas à la valeur de courrier électronique confirmée.',
		'firstname':'Sil vous plaît entrez votre nom.',
		'firstname_length':'Votre nom doit contenir au moins 2 caractères.',
		'nastname':'Veuillez entrer votre nom de famille.',
		'nast_nength':'Votre nom doit contenir au moins 2 caractères.',
		'address':'Veuillez entrer ladresse de livraison.',
		'address_length':'Votre adresse dexpéditeur doit comporter au moins 5 caractères.',
		'city':'Sil vous plaît entrez votre ville.',
		'city_length':'Votre ville doit comporter au moins 3 caractères.',
		'country':'Veuillez sélectionner votre pays de destination.',
		'state':'Veuillez sélectionner votre état / province / région',
		'taxcode':'Désolé, votre %str% est requis.',
		'taxcode_length':'Votre %str% doit contenir au moins %TaskL% de chiffres.',
		'tariff':'Désolé, votre valeur %str% est requise.',
		'tariff_length':'Votre %str% doit contenir au moins 12 chiffres.',
		'zip':'Veuillez entrer le code postal / code postal.',
		'zip_length':'Votre code postal / code postal doit comporter au moins 4 chiffres',
		'phone':'Veuillez entrer votre numéro de téléphone.',
		'phone_format':'Veuillez entrer un numéro de téléphone valide.',
		'phone_length':'Votre numéro de téléphone doit comporter au moins 7 caractères.',
		'Please_add_item':'Sil vous plaît ajouter larticle',
		'Input_AMT':"S'il vous plaît entrer le montant du paiement",
		}
	},
	
	'goods_info':{
		'Pleaselogin':'Veuillez vous connecter',
		'Successfulinclusion':'Inclusion réussie',
		'product_out':'Cet article est épuisé',
		'Please_purchased':"S'il vous plaît entrer la quantité achetée",
		'Added_successfully':'Ajouté avec succès',
		'Purchase_success':'Succès dachat',
		'No_Spec':'Ce produit n&#39;a pas été publié.',
		'Successful_Payment':'Paiement réussi!',
	},
	
	'my_inquiry_publish':{
		'submitsuccess':'Soumis avec succès',
		'completeinformation':'Veuillez compléter les informations',
		'thecompatibletitle':'Le titre est trop long, veuillez remplir le titre compatible',
		'thecorrectEmail':'Veuillez remplir le bon email',
		'thecorrectquantity':"S'il vous plaît remplir la quantité correcte",
		'goodsListerr':"Obtenir l'erreur de la liste des marchandises",
		'deletethisgoods':'Êtes-vous sûr de vouloir supprimer ces éléments?',
		'Pleaseselect_a_goods':'Veuillez sélectionner un article ou télécharger des photos pour que le fournisseur sache ce que vous voulez',
		'I18N_No':'I18N Non'
	},
	'addressfrom':{
		'Inserted_successfully':'Inséré avec succès',
		'Successfully_modified':'Modifié avec succès',
		'Enter_Query':'Entrez la requête',
		'Merchants_Settled':'Marchands installés',
		'Merchant_Side':'Côté marchand',
		'Please_Select_The_Shipping_Address':'Veuillez sélectionner ladresse de livraison',
		'Please_Select_The_Billing_Address':"Veuillez d'abord définir l'adresse de facturation.",
		'Submit_Order_Successfully':'Soumettre la commande avec succès',
		'No_Province':'Les dossiers provinciaux n&#39;existent pas',
		'Has_Residence':'Vous avez demandé la résidence, vous ne pouvez plus postuler!',
	},
	'Purchase_Center_Store':{
		'GuangZhou':"Point d'achat de Guangzhou",
		'Spain':"Point d'achat espagnol",
		'Chile':"Point d'achat Chili",
		'Romania':"Point d'achat roumain",
		'Hungary':"Point d'achat hongrois",
		'America':"Point d'achat américain",
		'Canada':"Point d'achat canadien",
		'Russia':"Point d'approvisionnement russe",
		'Belarus':"Point d'achat biélorusse",
		'Ukraine':"Point d'approvisionnement ukrainien",
		'Poland':"Point d'achat polonais",
		'Germany':"Point d'achat allemand",
		'Czekh':"Point d'approvisionnement tchèque",
		'England':"Point d'achat au Royaume-Uni",
		'Ireland':"Point d'achat irlandais",
		'Holland':"Point d'achat néerlandais",
		'France':"Point d'achat français",
		'Finland':"Point d'achat finlandais",
		'Sweden':"Point d'achat suédois",
		'Norway':"Point d'achat norvégien",
		'Denmark':"Point d'achat danois",
		'Korea':"Point d'achat en Corée",
		'Japan':"Point d'achat japonais",
		'Philippines':"Point d'achat aux Philippines",
		'Singapore':"Point d'achat de Singapour",
		'Israel':"Point d'achat d'Israël",
		'Turkey':"Point d'achat Turquie",
		'Australia':"Point d'achat australien",
		'New_Zealand':"Point d'achat en Nouvelle-Zélande",
		'South_Africa':"Point d'achat sud-africain",
	},
	supplier_entry_hint:{
		companyname:"Le nom de l'entreprise ne peut pas être vide! ",
		suppliercategory:"La classification des fournisseurs ne peut être vide! ",
		companyaddress:"L'emplacement de l'entreprise ne peut pas être vide! ",
		city:"La ville ne peut pas être vide! ",
		companyaddressinfo:"L'adresse détaillée de la société ne peut pas être vide! ",
		email:"E-mail ne peut pas être vide! ",
		noemail:"Format de courrier électronique illégal!",
		fund:"Les fonds d'inscription ne peuvent être vides! ",
		code:" Le code de crédit social ne peut pas être vide! ",
		legalperson:"Le nom de l'entreprise ne peut pas être vide! ",
		businesslicense:" La licence d'exploitation ne peut être valide! ",
		businesslicenseformat:" La période de validité de la licence d'exploitation est incorrecte. Le format correct est: 2010/01 / 01",
		entity:"Contact ne peut pas être vide! ",
		phone:"Le numéro de mobile ne peut pas être vide! ",
		accountopeningbank:"La banque du compte de règlement ne peut pas être vide! ",
		bankaccount:"Le numéro de compte bancaire ne peut pas être vide! ",
		openingbankname:" Le nom de la succursale bancaire ne peut être vide! ",
		openingbankaddress:" L'emplacement de la succursale bancaire ne peut pas être vide! ",
		Legalperson_idnumber_passportnumber:"Le numéro de carte d'identité / passeport d'entreprise ne peut être vide! ",
		Operationdirector_idnumber_passportnumber:"Le numéro d'identification / passeport ne peut pas être vide! ",
		Enterprisecertificate:" besoin de télécharger les informations d'identification de l'entreprise! ",
		legalperson_photo_front:"Vous devez télécharger une carte d'identité / un passeport légal! ",
		legalperson_photo_reverseside:"Vous devez télécharger une photo d'identité / photo d'identité avec passeport! ",
		Operationdirecto_photo_front:" Exige le téléchargement de la personne responsable de l'identification de l'opération / du passeport avant! ",
		Operationdirecto_reverseside:" besoin de télécharger l'opération identifiant personne responsable / passeport inversé! "
	},
	mobile:{
		Size:'Taille:',
		Color:'Couleur:',
		Order_Number:'Numéro de commande.',
		Double:'Double',
		Total_Price:'Prix ​​total:',
		Shop:'Magasin:',
		No_Data:'Aucune donnée',
		Collection:'Collection',
		Add_To_Shopping_Cart:'Ajouter au panier',
		Whole:'Tous',
		Collection1:'Collection réussie',
		Collection2:'Annuler la collection',
		Collection3:'Collection échoué',
		Go_To_Set:'Aller à Set',
		Set_Later:'Régler après',
		Tips:'Conseils',
		Out_Of_Stock:'En rupture de stock',
		Inventory_Shortage:'Manque d&#39;inventaire, s&#39;il vous plaît re-remplir',
		
		Confirm_receipt:"Confirmer la réception?",
		Successful_receipt:"Reçu avec succès",
		First_page:"C'est déjà la première page",
		The_last_page:"C'est la dernière page",
		Acquisition_failed: "Get failed",
		Failed_to_send: "Send failed",
		Successful_recovery: "La récupération est réussie",
		all_reviews: "Tous les commentaires",
	},
	cart3:{
		name:'Nom',
		Contents:'Remarques',
		SentMoney:'Remise',
		Number:'Numéro de référence',
		Currency:'devise',
	},
	home:{
		brneficiary:'Bénéficiaire',
		bankaddress:'Adresse bancaire',
		swiftcode:'Code SWIFT',
		beneficiarybank:'Banque bénéficiaire',
		beneficiaryaccount:'Compte bénéficiaire',
		depos_it_bank_subbranch_ascription:'Titulaire du compte de règlement',
		account_name:'Compte bancaire',
		depos_it_bank_subbranch:'Nom de la succursale bancaire',
		bank_account:'Succursale de la succursale bancaire',
	},
	supplier:{
		products:'Produit',
		productsStyle:'Méthode de traitement'
	}
};