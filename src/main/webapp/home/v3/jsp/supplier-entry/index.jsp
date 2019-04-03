<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/home/v3/header.jsp"></jsp:include>
<link rel="stylesheet" href="/home/v2/static/css/nav/new-top-nav-style.css"/>
<link rel="stylesheet" href="/home/v3/static/css/supplierEntry/index.css"/>
</head>
<body>
<jsp:include page="/home/v3/nav-nobody.jsp"></jsp:include>
<div id='shopenter'>
    <index-top></index-top>
    <div class="msgcon">
        <div class="enterstep">
            <div class="hen left"></div>
            <div class="hen center"></div>
            <div class="hen right"></div>
            <div class="steps">
                <div class="step" v-for="item,index in steps">
                    <div>
                        <img src="/home/v3/static/images/supplierEntry/stepback.png" alt="" :class="{showstep : step >= index}">
                        <span>{{index + 1}}</span>
                    </div>
                    <p>{{item}}</p>
                </div>
            </div>
        </div>
        <div class="step1" v-if="step == 0">
            <div class="conditioncon">
                <h3>协议确认</h3>
                <div class="condition">
                    <p style="color:black;font-weight: bold">请仔细阅读本条款与条件！请注意免除或限制责任和条款的规定</p>
                    <p style="color:black;font-weight: bold">1.接受条款</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">1.1</span>欢迎使用鞋贸港的免费服务（“服务”）。以下规定了您（“会员”）和shoestp.com公司（“鞋贸港”)之间签订的鞋贸港免费会员资格协议( “本协议”）的条款和条件，根据本协议，鞋贸港通过网址为www.shoestp.com的网站（ “网站”）向您提供使用服务的权限。使用服务即说明您接受下述条款和条件。如果您不接受任何条款和条件，请勿使用服务。通过完成注册流程并点击“我同意”按钮，即表示您同意受本协议的约束。除非并且直至您激活您的账户，否则，鞋贸港免费会员资格协议不会生效。本协议中未定义的术语应具有使用条款中包含的相同含义。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">1.2</span>鞋贸港可随时通过在网站发布经修订和重述的协议修订本协议。经修订和重述的协议在公示指定日期且公示至少7天后生效。鞋贸港发布经修订和重述的协议后，如您继续使用服务，则应被视为接受经修订的条款。</p>
                    <p style="color:black;font-weight: bold">2.服务</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">2.1</span>服务将免费持续提供，除非根据本协议的条款终止。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">2.2</span>服务应具有下列核心功能（鞋贸港可自行决定并在通知您之后不时添加、修改或为计划内或计划外的维护目的而暂停该等功能）（“免费会员利益”)：</p>
                    <p style="margin-bottom: 5px;padding-left: 20px;">   a)公司简介-允许每位会员展示和编辑有关其企业的基本信息，例如成立年份和地点、预计年销售额、雇员人数以及提供的产品和服务等。</p>
                    <p style="margin-bottom: 5px;padding-left: 20px;">   b)产品-允许每位会员展示并编辑不少于5种产品的描述、规格和图像。</p>
                    <p style="margin-bottom: 5px;padding-left: 20px;">   c)无限量发布买方求购信息- 允许每位会员在网站上发帖，供公开展示从网站的其他用户处购买产品和服务的要约。</p>
                    <p style="margin-bottom: 35px;padding-left: 20px;">   d)享用平台海外推广所带流量</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">2.3</span>鞋贸港可在发出通知后，根据通知指定时间单方中止或终止全部或部分上述免费会员利益。鞋贸港保留自行决定就服务或服务的任何特征或功能收费的权利，但应提前通知会员。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">2.4</span>提供给会员的利益、特征和功能可能根据国家和地区的不同而不同。除明确约定外，鞋贸港不就提供特定特征或功能或提供相同类别和范围的特征和功能作任何保证或陈述。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">2.5</span>鞋贸港在网站上向任何会员提供任何交易特征和功能可以由其和/或经其批准的独立第三方对会员身份和/或其指定银行账户进行验证为前提条件。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">2.6</span>鞋贸港应向每位会员提供会员用户名和密码（后者应由会员在注册过程中选择），以便通过该等会员账户使用服务。每位会员应就维持其会员用户名和密码的保密性以及在该会员用户名和密码项下发生的所有活动承担全部责任。一套会员用户名和密码专属于唯一的账户，会员不得与其业务实体以外的另一名人士分享、转让或允许该人士使用其账户、会员用户名或密码。每位会员均确认，与其他人士分享其账户或允许其业务实体之外的多个用户使用其账户（统称“多人使用”）可能对鞋贸港造成不可修复的伤害，每位成员应就鞋贸港因多人使用账户而遭受的任何损失或损害（包括但不限于利润损失）赔偿鞋贸港。每位会员均在此承诺，如果有人擅自使用其账户、会员用户名或密码或发生违反安全性的任何其他行为时，其将立即通知鞋贸港。每位会员均在此同意，鞋贸港不会因会员未遵守本款产生的任何损失或损害承担任何责任。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">2.7</span>鞋贸港保留未经事先通知随时暂时性或永久性更换、升级、修改、限制或暂停服务或服务的任何相关功能或应用的权利。鞋贸港进一步保留为服务或将来的服务版本引入新特征、功能或应用或条件的权利。除非鞋贸港另行规定，所有新特征、功能、应用、条件、修改、升级和变更均应适用本协议。 </p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">2.8</span>每位会员均确认，因任何原因无法使用全部或部分服务可能对其业务产生不利影响。每位会员均在此同意，鞋贸港在任何情况下均不会就无法使用服务（无论是因为服务中断、变更或终止还是其他原因）、全部或任何部分服务的任何通讯或传输或交付过程中的任何延迟、不准确、错误或遗漏或因使用服务或无法使用服务而产生的任何损害（无论直接的、间接的、衍生的或其他的）对会员或任何第三方承担任何责任。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">2.9</span>如果您有下列行为，鞋贸港保留不经事先通知自行决定暂停、限制或拒绝您访问或使用由鞋贸港网站提供的服务：(a) 使用鞋贸港网站提供的服务对任何人士或实体进行欺诈；(b) 从事任何非法活动，包括但不限于会构成知识产权侵权、民事责任或刑事犯罪的活动；(c) 从事以其他方式导致鞋贸港网站承担任何责任的任何活动。</p>
                    <p style="color:black;font-weight: bold">3.会员责任</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">3.1</span>每位会员均在此陈述、保证和同意：(a)提供鞋贸港可能要求的、关于其自身及其业务推荐的真实、准确、最新和完整的信息，并且(b)维护并立即修订所有信息，使其保持真实、准确、最新和完整。每位会员均在此向鞋贸港授予不可撤销的、永久性的、世界范围内、免使用费的、可（多层级）分许可的许可，以便鞋贸港可通过现在已知或未知的任何媒介展示和使用该会员根据本协议中所述目的提供的所有信息，并行使您对该资料或信息拥有的著作权、宣传权和数据库权。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">3.2</span>每位会员均在此陈述、保证和同意，其对服务和网站的使用不会：</p>
                    <p style="margin-bottom: 5px;padding-left: 20px;">    a)包含欺诈性信息，或就物品作出欺诈性要约，或涉及销售或试图销售假货、赃物或适用法律禁止销售和/或营销的物品，或以其他方式促进其他违法活动；</p>
                    <p style="margin-bottom: 5px;padding-left: 20px;">    b)构成诈骗其他会员或网站其他用户或出于任何其他违法目的计划的一部分；</p>
                    <p style="margin-bottom: 5px;padding-left: 20px;">    c)侵犯或以其他方式教唆或鼓励侵犯或违反任何第三方的著作权、专利、商标、商业秘密、其他专有权利、公开宣传权、隐私权或其他合法权利；</p>
                    <p style="margin-bottom: 5px;padding-left: 20px;">    d)冒充任何人士或实体，或就您自身或您与任何人士或实体的关联关系作出虚假陈述；</p>
                    <p style="margin-bottom: 5px;padding-left: 20px;">    e)违反任何适用法律、法令、条例或法规（包括但不限于管辖出口管控、消费者保护、不公平竞争、反歧视或虚假广告的法律法规、法令、条例）；</p>
                    <p style="margin-bottom: 5px;padding-left: 20px;">    f)包含诽谤、诋毁、非法威胁或非法骚扰他人的信息；</p>
                    <p style="margin-bottom: 5px;padding-left: 20px;">    g)包含色情信息，或包含或暗示任何色情内容、与性相关的商品、任何其他性相关内容、以其他方式推广色情内容，或以其他方式对未成年人有害；</p>
                    <p style="margin-bottom: 5px;padding-left: 20px;">    h)鼓动基于种族、性别、宗教、国籍、残疾、性取向或年龄进行歧视的信息；</p>
                    <p style="margin-bottom: 5px;padding-left: 20px;">    i)包含构成擅自广告或骚扰（包括但不限于发送垃圾邮件），侵犯任何人隐私，或教唆构成刑事犯罪、引发民事责任或以其他方式违反任何法律法规的行为；</p>
                    <p style="margin-bottom: 5px;padding-left: 20px;">    j)涉及企图复制、复印、利用或征用鞋贸港的各种专有目录、数据库和列表；</p>
                    <p style="margin-bottom: 5px;padding-left: 20px;">    k)涉及任何计算机病毒或其他具有损坏、干预、拦截或利用任何软件或硬件系统、数据或个人信息之效果的破坏性设备和代码；及</p>
                    <p style="margin-bottom: 5px;padding-left: 20px;">    l)涉及威胁鞋贸港和/或服务的任何用户所使用的计算机系统或网络安全性的任何计划，并且任何会员均不得尝试擅自访问该等计算机系统或网络；</p>
                    <p style="margin-bottom: 35px;padding-left: 20px;">    m)直接或间接链接至或包含违反任何法律法规或在本协议或使用条款项下被禁止的商品或服务的描述或其他材料；或以其他方式使鞋贸港或其关联方承担任何责任。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">3.3</span>每位会员均陈述、保证并同意，就与任何业务推荐人相关的信息或代表任何业务推荐人发布的信息而言，其已从其商业伙伴和关联方处获得进行以下行为所需的所有同意、批准和豁免：(a) 作为该会员的业务推荐人行事；(b) 代表其发布和公布其联系方式和信息、推荐信和评价；并且(c) 第三方可联系该等业务推荐人，要求其关于该会员作出的主张或声明提供支持。 每位会员均进一步保证，所有推荐信和评价均是真实准确的，且其在此放弃在第三方联系业务推荐人之前获得其同意的所有要求。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">3.4</span>会员不得采取可能损害鞋贸港反馈系统诚信度的任何行为，例如使用其他会员用户名或通过第三方为其自身创建正面反馈或为其他会员创建无根据的负面反馈。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">3.5</span>每位会员均确认并同意，鞋贸港没有义务主动监控通过服务创建、获得或可访问的任何消息或其他材料或信息的内容，也没有义务对该等内容行使任何编辑控制权。鞋贸港不对任何会员提供的任何评价或其他材料或信息的内容作出背书、验证或其他证明。每位会员均应对其通讯的内容承担全部责任，并且可能会因为其评价或其他材料或信息的内容而被追究法律责任或被追责。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">3.6</span>每位会员均陈述、保证并同意，其已获得所有必要的第三方授权和许可，并负责确保其在网站上发布、提供给鞋贸港或授权鞋贸港展示的任何材料或信息均不会违反任何第三方的著作权、专利、商标、商业秘密或任何其他私人或专有权利，或系经该等权利的所有人允许而发布该等材料或信息。每位会员均进一步陈述、保证和同意，其有权利和权限销售、经销或要约销售或经销其在网站上发布的、提供给鞋贸港的或授权鞋贸港展示的材料或信息中描述的产品。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">3.7</span>如果任何会员违反上述第3.1条、第3.2条、第3.3条、第3.4条、第3.5条或第3.6条中的陈述、保证和约定，或者鞋贸港有合理理由认为该会员违反该等陈述、保证和约定，或者如果在任何其他会员或第三方投诉或索赔后鞋贸港有合理理由认为该会员故意或实质性未履行其与该第三方的合同（包括但不限于会员未能在收到货款后交付该第三方订购的任何货品，或者会员交付的货品实质性不符合其与该第三方的合同中概述的条款和描述），或者如果鞋贸港有合理理由相信该会员在任何在线交易中使用了偷来的信用卡或其他虚假或误导性信息，鞋贸港有权暂停或终止与该会员有关的服务和所有免费会员权利，不作任何赔偿，并限制或拒绝其现在或将来使用服务或鞋贸港可能提供的任何其他服务。另外，鞋贸港保留根据其自行决定采取下述行为的权利：对会员可在鞋贸港认为合适的期限内在网站上发布的产品列表数量进行限制，或删除其合理认为违法、可能使鞋贸港承担责任、违反本协议或使用条款或鞋贸港认为不适当的任何材料。鞋贸港保留在就任何涉嫌刑事或民事违法行为的过程中与政府机关、私营调查机构和/或受损害的第三方进行全面合作的权利。并且，如果政府、执法机构或受损害的第三方要求，或由于传票或其他法律行动，鞋贸港可披露会员的身份和联系方式，并且鞋贸港不对因此产生的损害或结果承担任何责任，而会员同意不就该披露对鞋贸港提起任何诉讼或索赔。就上述任一项，鞋贸港可自行决定以其认为合适的方式暂停或终止任何会员的账户。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">3.8</span>每位会员均同意，就因其提交、发布或删除材料、因其使用服务或因其违反本协议或使用条款可能产生的任何和所有损失、权利主张和责任（包括在全额赔偿基础上的法律费用）赔偿鞋贸港及其雇员、代理和代表，并使鞋贸港及其雇员、代理和代表免于损害。每位会员均进一步同意，鞋贸港不就该会员或第三方发布的任何材料（包括欺诈性、不真实、误导性、不准确、诋毁性、冒犯性或非法的材料）对该等材料或任何其他人士承担任何责任，该等材料造成的损害风险应完全由每位会员自行承担。鞋贸港保留自费就需由会员赔偿的任何事项进行排他性抗辩和控制的权利，在此情况下，会员应与鞋贸港合作提出任何可用抗辩。</p>
                    <p style="color:black;font-weight: bold">4.买方和卖方之间的交易</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">4.1</span>鞋贸港通过网站提供电子网络平台，供买方和卖方在线交换信息并达成产品和服务的买卖交易。鞋贸港保留就规定的会员限制某些平台特征和功能的权利。尽管通过网站提供了平台，但鞋贸港不在具体交易中代表卖方或买方，无论该等交易是否在网站上或通过网站进行。鞋贸港就在网站上要约出售的产品或服务的质量、安全性、合法性或可获得性、卖方完成销售的能力或买方完成购买的能力不作任何控制，也不承担任何责任。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">4.2</span>会员在此被告知，存在其可能与有欺诈行为的人士进行交易的风险。鞋贸港采用了某些技术，以验证用户在网站上注册时提供的信息的准确性。但是，由于在互联网上进行用户验证比较困难，鞋贸港无法也不会确认网站上显示的每位免费会员所声称的身份，鞋贸港仅能尽合理努力，以根据中国大陆的适用法律验证在www.shoestp.com上开店的卖方代表在中国大陆的个人身份。我们建议您使用网站上的各种可用工具及常识来评估与您进行交易的对手方。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">4.3</span>每位会员均承认，其在使用网站开展交易时将完全承担买卖交易的风险。该等风险应包括但不限于产品和服务失实陈述、欺诈方案、质量不合格、未满足技术规格、产品缺陷、交付或付款延误或违约、成本误算、违反质保、违反合同以及运输事故（统称为“交易风险”）。每位会员均同意，除法律规定外，鞋贸港无需就可能因为任何交易风险而产生或与之相关的任何类型的任何损害、责任、费用、伤害、不便、业务中断或开支负责。 </p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">4.4</span>会员应对在网站上开展、通过网站开展或因为使用网站而导致的交易的所有条款和条件完全负责，包括但不限于有关付款、退货、质保、装运、保险、费用、税费、所有权、授权、罚款、许可、搬运、运输和存储的条款。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">4.5</span>会员同意提供鞋贸港可能合理要求的与其通过网站上的交易平台开展的交易相关的所有信息和材料。如果任何会员未能提供要求的信息和材料，鞋贸港有权中止或终止该等会员的账户。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">4.6</span>如果任何会员与某一交易的任何一方发生争议，该等会员同意就因为该等交易而产生或与之相关的所有权利主张、要求、诉讼、法律程序、费用、开支和损害赔偿（包括但不限于任何实际、特殊、附带或衍生的损害赔偿）豁免鞋贸港（和鞋贸港的代理、关联方、董事、管理人员和雇员）的责任并作出赔偿。 </p>
                    <p style="color:black;font-weight: bold">5.使用网站讨论区</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">5.1</span>鞋贸港向其会员免费提供网站讨论区，推动和鼓励在我们所有会员之间开展公开、诚实和有礼貌的沟通。网站讨论区不得被会员用来作为营销平台，且会员不得发布有关贸易机会、其产品推广或其公司资料的任何信息。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">5.2</span>每位会员均承认，通过网站讨论区公开发布或私下传播的所有数据、文本、软件、音乐、音频、照片、图像、视频、消息或其他材料（“内容”）均由提供该内容的会员承担全部责任。换言之，除非鞋贸港存在过错，通过网站讨论区上传或发布的所有内容应由发布内容的会员而非鞋贸港承担全部责任。除法律规定外，鞋贸港对通过讨论区发布的内容不做任何控制，因此，鞋贸港不保证该等内容的准确性、完整性或质量。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">5.3</span>鞋贸港保留在不提前通知的情况下自行决定删除或编辑任何发布内容的权利。鞋贸港可以监督违反本协议的任何会员的发布活动，并限制其在网站讨论区发布信息的能力。鞋贸港在任何情况下均无需以任何方式就任何内容（包括但不限于任何内容中的任何错误或遗漏）或就因为该等会员使用讨论区而导致的任何类型的任何损失或损害承担责任，法律规定鞋贸港应该承担责任的除外。每位会员均同意评估和承担与使用任何内容（包括对其准确性或完整性的任何依赖）相关的所有风险。每位会员均理解，若使用网站上的鞋贸港讨论区，其可能会接触到攻击性、不得体的或令人反感的内容。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">5.4</span>在不损害每位会员在本协议第3条项下责任的前提下，每位会员均同意不会将网站讨论区用于以下目的：</p>
                    <p style="margin-bottom: 5px;padding-left: 20px;">   a)上传、发布或通过电子邮件发送任何非法、有害、威胁、辱骂、骚扰、欺骗性、诽谤、粗俗、淫秽、损害名誉、侵犯他人隐私、煽动仇恨、种族歧视、民族歧视或以其他方式令人反感的内容；</p>
                    <p style="margin-bottom: 5px;padding-left: 20px;">   b)以任何方式伤害未成年人；</p>
                    <p style="margin-bottom: 5px;padding-left: 20px;">   c)冒充任何人士或实体，虚假声明或以其他方式就您与某一人士或实体的从属关系作出不实陈述，或伪造任何内容的来源；</p>
                    <p style="margin-bottom: 5px;padding-left: 20px;">   d)“跟踪”或以其他方式骚扰他人；</p>
                    <p style="margin-bottom: 5px;padding-left: 20px;">   e)收集或存储有关其他用户的个人资料；</p>
                    <p style="margin-bottom: 5px;padding-left: 20px;">   f)上传、发布或通过电子邮件发送根据任何法律、契约关系或信托关系您无权传播的任何内容；</p>
                    <p style="margin-bottom: 5px;padding-left: 20px;">   g)上传、发布或通过电子邮件发送侵犯任何一方的任何知识产权或其他合法权利的任何内容；</p>
                    <p style="margin-bottom: 5px;padding-left: 20px;">   h)上传、发布或通过电子邮件发送任何未经请求或未经授权的广告、推广材料、“垃圾邮件”、“垃圾信息”、“连环信”或任何其他形式的招揽； </p>
                    <p style="margin-bottom: 5px;padding-left: 20px;">   i)上传、发布或通过电子邮件发送包含电脑病毒或旨在中断、摧毁或限制任何电脑软件、硬件或通讯设备的功能的任何其他电脑代码、文件或程序的任何内容； </p>
                    <p style="margin-bottom: 5px;padding-left: 20px;">   j)上传、发布或通过电子邮件发送任何包含有关鞋贸港服务的投诉或在网站上或向任何其他会员提及该等投诉的内容；任何该等投诉均必须直接发送至网站的客户服务电子邮箱；或 </p>
                    <p style="margin-bottom: 35px;padding-left: 20px;">   k)违反任何适用的国家或内部法律或规定。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">5.5</span>每位会员均承认，鞋贸港不对内容进行事先过滤，但鞋贸港应有权（但无义务）自行决定调整、修改或删除网站讨论区发布或上传的任何内容。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">5.6</span>每位会员均授予鞋贸港永久的、全球性的、免使用费的、不可撤销的且非排他性的许可（包括多层级分许可的权利），鞋贸港可以（全部或部分）使用、复制、修改、改编、发布、翻译、创造衍生品、分发、演出或展示由该等会员上传、发布或提供给鞋贸港用于在网站上发布的任何内容，以及/或将该等内容以任何当前已知或已开发的形式、媒介或技术与其他作品进行合并。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">5.7</span>每位会员均应就与该等会员上传、发布或通过电子邮件发送至网站讨论区的任何内容、该等会员使用网站讨论区或该等会员违反第5.4条中的规定相关的任何第三方权利主张导致的任何直接或间接损失或损害（包括衍生损失和利润损失、商誉损失或业务机会损失）向鞋贸港及其子公司、关联方、雇员、管理人员、代理或合作伙伴作出赔偿，并使其免于损害。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">5.8</span>一旦知晓任何该等违约之后，鞋贸港可以禁止或删除与该等违约相关或鞋贸港基于其自由裁量权认为有损公众或鞋贸港或其任何关联方、许可方、合作伙伴或会员的权利的任何内容。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">5.9</span>鞋贸港保留采取其认为为防止会员违反第5.4条而必须采取的任何行动的权利，该等行动包括以下各项：</p>
                    <p style="margin-bottom: 5px;padding-left: 20px;">   a)（若鞋贸港认为违约情节不严重）向相关会员发出警告信；或</p>
                    <p style="margin-bottom: 5px;padding-left: 20px;">   b)（若鞋贸港认为违约情节严重）禁止相关会员参与网站讨论区。</p>
                    <p style="margin-bottom: 35px;padding-left: 20px;">   所有事件均会记录在案，并且鞋贸港在所有该等情况下作出的决定应是终局的。</p>
                    <p>5.10由鞋贸港服务团队、会员或第三方合作伙伴发布在网站上的所有信息和/或其他内容仅供参考，在任何情况下均不得被理解为法律和/或商业建议或法律意见书。在该等情况下，会员应当寻求独立的专家意见。</p>
                    <p style="color:black;font-weight: bold">6.责任限制</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">6.1</span>在法律允许的最大范围内，服务将按“现状”和“可供状态”提供，且鞋贸港在此明确表示，除法律规定要求外，拒绝作出任何和所有明示或默示保证，包括但不限于任何有关条件、质量、耐用性、性能、准确性、可靠性、适销性、适用于特定用途或不侵权的保证。所有该等保证、陈述、条件、承诺和条款均在此予以排除。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">6.2</span>在法律允许的最大范围内，鞋贸港就网站上或通过网站提供的任何信息的有效性、准确性、可靠性、质量、稳定性、完整性或时效性不作任何陈述或保证。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">6.3</span>通过使用服务下载或以其他方式获取任何资料完全基于每位会员的自行裁量并由每位会员自行承担风险，并且每位会员应自行承担因为下载任何该等资料导致其电脑系统损坏或数据丢失的责任。任何会员从鞋贸港、通过服务或从服务获取的口头或书面建议或信息均不会创设本协议中未明确声明的任何保证。 </p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">6.4</span>网站可以向用户提供由独立第三方提供的服务或产品。鞋贸港就该等服务或产品不作任何保证或陈述。鞋贸港及其关联方在任何情况下均不对任何该等服务或产品负责。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">6.5</span>鞋贸港在任何情况下均不就因为超出其合理控制的自然灾害、外力或原因直接或间接导致的服务延迟或故障或中断承担责任，包括但不限于网络故障、电脑、电信或任何其他设备故障、停电、罢工、劳动争议、暴动、叛乱、内乱、劳动力或材料短缺、火灾、水灾、风暴、爆炸、自然灾害、战争、政府行动、国内外法院或法庭的命令或第三方违约。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">6.6</span>除法律规定外，鞋贸港无需承担基于合同、过失、侵权行为或任何其他原因的任何特殊、直接、间接、惩罚性、附带或衍生性的损害赔偿或任何其他损害赔偿（包括但不限于对利润或储蓄损失、业务中断、信息丢失的损害赔偿）或因为以下任何一项导致的任何其他损害赔偿：</p>
                    <p style="margin-bottom: 5px;padding-left: 20px;">   a)使用服务或无法使用服务；</p>
                    <p style="margin-bottom: 5px;padding-left: 20px;">   b)通过网站从某一会员或第三方服务提供商处购买或获得的商品、样品、数据、信息或服务存在任何缺陷；</p>
                    <p style="margin-bottom: 5px;padding-left: 20px;">   c)第三方未经授权获取任何会员的数据或私有信息；</p>
                    <p style="margin-bottom: 5px;padding-left: 20px;">   d)网站的任何用户的声明或行为；或</p>
                    <p style="margin-bottom: 35px;padding-left: 20px;">   e)因为任何原因（包括过失）产生的与服务相关的任何其他事项。</p>
                    <p>6.7即使存在任何前述规定，但鞋贸港及其员工、代理、关联方、代表或代表其行事的任何人就因为使用服务或网站产生的所有权利主张向每位会员承担的责任总额应限制在100元人民币之内。前一句规定并不豁免会员需证明实际损害的要求。因使用服务而产生的所有权利主张均须在诉由产生之日起一（1）年内或管辖本协议的任何法律项下规定的更长期限内提起。</p>
                    <p style="color:black;font-weight: bold">7.知识产权</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">7.1</span>鞋贸港是服务的所有权利的唯一所有者或合法被许可方。服务包含了受全球版权法律和其他法律保护的商业机密和知识产权。服务中的所有权利、所有权和知识产权均应始终属于鞋贸港或其关联方。本协议项下未另行主张或未由鞋贸港另行主张的所有权利均在此保留。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">7.2</span>“SHOESTP（鞋贸港）”、“SHOESTP.COM”和相关图标和标识均为新联实业有限公司的注册商标或商标或服务标志，受到适用的版权法、商标法和其他专有权法律的保护。未经授权，严禁对该等标志进行复制、修改、使用或公布。</p>
                    <p style="color:black;font-weight: bold">8.通用条款</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">8.1</span>本协议和使用条款构成会员与鞋贸港之间就服务的使用达成的完整协议。本协议和使用条款管辖服务的使用，并取代就本协议中同一标的达成的任何先前的书面或口头协议。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">8.2</span>鞋贸港与会员系独立的缔约方。本协议不旨在创设也未曾创设任何代理、合伙、合资、雇佣或特许关系。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">8.3</span>如果本协议任何条款被裁定为无效或不可强制执行，该等条款应被删除，而其余条款仍应予以强制执行。 </p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">8.4</span>标题仅作参考，在任何情况下均不规定、限制、解释或描述该条款的范围或程度。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">8.5</span>鞋贸港未执行任何权利或未就本协议项下会员的任何违约采取行动不构成对该权利的放弃，也不表示鞋贸港放弃就后续或类似违约采取行动的权利。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">8.6</span>鞋贸港应有权向任何人士或实体（包括鞋贸港的任何关联方）转让本协议（包括其在本协议中的所有权利、所有权、利益、权益、义务和责任）。会员不得向任何人士或实体全部或部分转让本协议。</p>
                    <p><span style="color:black;font-weight: bold;margin-right: 15px">8.7</span>本协议应受中华人民共和国（“中国”）法律管辖，并且本协议双方在此服从中国法院的专属管辖。</p>
                </div>
            </div>
            <div class="checkcon">
                <el-checkbox v-model="checkconditiong">我已阅读并同意接受以上所有协议</el-checkbox>
                <div @click="agree" :class="{allowed : checkconditiong}">同意并继续</div>
            </div>
        </div>
        <div class="step2" v-if="step == 1">
            <!--公司信息 and 运营信息-->
            <div class="companyForm">
                <!--公司信息-->
                <p class="companyForm-title">公司信息</p>
                <el-form label-position="right" label-width="150px" :model="basicInfo" :rules="firstform"
                         ref="basicInfo">
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="*公司名称:" prop="name">
                                <el-input v-model="basicInfo.name" :class="{'null' : !basicInfo.name}"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="*公司英文名称:" prop="englishName">
                                <el-input v-model="basicInfo.englishName"
                                          :class="{'null' : !basicInfo.englishName}"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="公司类型">
                                <el-select v-model="basicInfo.companyType" placeholder="请选择">
                                    <el-option label="有限责任公司" value="有限责任公司"></el-option>
                                    <el-option label="股份有限公司" value="股份有限公司"></el-option>
                                    <el-option label="无限责任公司" value="无限责任公司"></el-option>
                                    <el-option label="两合公司" value="两合公司"></el-option>
                                    <el-option label="股份两合公司" value="股份两合公司"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="公司性质">
                                <el-select v-model="basicInfo.companyNature" placeholder="请选择">
                                    <el-option label="国有企业" value="国有企业"></el-option>
                                    <el-option label="集体企业" value="集体企业"></el-option>
                                    <el-option label="私营企业" value="私营企业"></el-option>
                                    <el-option label="个体工商户" value="个体工商户"></el-option>
                                    <el-option label="合伙企业" value="合伙企业"></el-option>
                                    <el-option label="联营企业" value="联营企业"></el-option>
                                    <el-option label="股份合作制企业" value="股份合作制企业"></el-option>
                                    <el-option label="有限责任公司" value="有限责任公司"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="企业成立时间">
                                <el-date-picker type="date" placeholder="选择日期" v-model="basicInfo.companyEstablishTime"
                                                style="width: 100%;" format="yyyy 年 MM 月 dd 日"
                                                value-format="yyyy-MM-dd"></el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="*公司详细地址:" prop="companyAddr">
                                <el-input v-model="basicInfo.companyAddr"
                                          :class="{'null' : !basicInfo.companyAddr}"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="公司电话:" prop="telephone">
                                <el-input v-model="basicInfo.telephone" placeholder="15266666666"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="邮编:" prop="postcode">
                                <el-input v-model="basicInfo.postcode"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="传真:" prop="fax">
                                <el-input v-model="basicInfo.fax"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="公司官网地址:" prop="website">
                                <el-input v-model="basicInfo.website" placeholder="http(s)://"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12" class="targetmarket">
                            <el-form-item label="*目标市场:" prop="targetedMarket">
                                <div class="countrycon">
                    <span class="countrybotton" @click="showselectcon">
                      你已选择{{basicInfo.targetedMarket.length}}个国家
                    </span>
                                    <div class="select" :class="iscountryshow? '' : 'selecthidden'" style="z-index:99;top:100%;">
                                        <div class="showselect" v-show="basicInfo.targetedMarket != 0">
                        <span v-for="item,index in selectcountry">
                          {{item.name}}
                          <i class="el-icon-error" style="cursor: pointer;opacity: 0.4;"
                             @click="deletecountry(index)"></i>
                        </span>
                                        </div>
                                        <el-input placeholder="请输入内容" prefix-icon="el-icon-search"
                                                  v-model="countrysearch" v-on:input="filterCountry"></el-input>
                                        <div class="countrychoose">
                                            <el-checkbox-group v-model="basicInfo.targetedMarket"
                                                               @change="selectshow">
                                                <el-checkbox v-for="item in exhibitionCountry"
                                                             :label="item.id"
                                                             :key="item.id">
                                                    {{item.name}}
                                                </el-checkbox>
                                            </el-checkbox-group>
                                        </div>
                                    </div>
                                </div>
    <div style="position: fixed;top:0;left:0;width: 100%;height: 100%;background-color: transparent;z-index:98" v-show="iscountryshow" @click="iscountryshow = !iscountryshow"></div>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="年产量:" prop="annualProduction">
                                <el-input v-model="basicInfo.annualProduction" style="width: 88%;"></el-input>双
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="*生产模式:" prop="prodPattern">
                                <el-checkbox-group v-model="basicInfo.prodPattern"
                                                   :class="{'null' : basicInfo.prodPattern.length == 0}">
                                    <el-checkbox label="OEM">OEM</el-checkbox>
                                    <el-checkbox label="ODM">ODM</el-checkbox>
                                </el-checkbox-group>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="*统一社会信用代码:" prop="creditCode">
                                <el-input v-model="basicInfo.creditCode" v-on:input="toUpper"
                                          :class="{'null' : !basicInfo.creditCode}"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="注册资本:" prop="registeredCapital">
                                <el-input v-model="basicInfo.registeredCapital" style="width: 88%;"></el-input>万
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="法定代表人:" prop="entity">
                                <el-input v-model="basicInfo.entity"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">

                            <el-form-item label="营业执照的有效期:" class="LicenseTime">
                                <el-date-picker type="date" placeholder="开始日期"
                                                v-model="basicInfo.businessLicenseBeginTime"
                                                style="width: 30%;" format="yyyy-MM-dd"
                                                value-format="yyyy-MM-dd"
                                                prefix-icon="none"></el-date-picker>
                                -
                                <el-date-picker type="date" placeholder="结束日期"
                                                :disabled="basicInfo.businessLicenseIsSecular == 1"
                                                v-model="basicInfo.businessLicenseEndTime"
                                                style="width: 30%;" format="yyyy-MM-dd"
                                                value-format="yyyy-MM-dd"
                                                prefix-icon="none"></el-date-picker>
                                <el-checkbox v-model="basicInfo.businessLicenseIsSecular" true-label="1"
                                             false-label="0">长期
                                </el-checkbox>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="纳税人类型:">
                                <el-select v-model="basicInfo.taxpayerType" placeholder="请选择">
                                    <el-option label="自然人" value="自然人"></el-option>
                                    <el-option label="个体工商户" value="个体工商户"></el-option>
                                    <el-option label="法人" value="法人"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <!-- </el-form> -->
                    <!--运营信息-->
                    <p class="companyForm-title">运营信息</p>
                    <!-- <el-form label-position="right" label-width="150px" :model="basicInfo" :rules="firstform"> -->
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="联系人姓名" prop="contacts">
                                <el-input v-model="basicInfo.contacts"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="联系人部门" prop="department">
                                <el-input v-model="basicInfo.department"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="联系人职称" prop="jobTitle">
                                <el-input v-model="basicInfo.jobTitle"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="联系人手机" prop="phone">
                                <el-input v-model="basicInfo.phone" placeholder="15266666666"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="联系人邮箱" prop="contactEmail">
                                <el-input v-model="basicInfo.contactEmail"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
                <div class="bottons">
                    <div class="botton botton1" @click="goback">上一步</div>
                    <div class="botton botton2" @click="formSubmit">下一步(资质上传)</div>
                </div>
            </div>
        </div>
        <div class="step3" v-if="step == 2">
            <h3>公司信息</h3>
            <el-form :model="basicInfo" :rules="secondform" ref="secondbasicInfo">
                <div class="tips">
                    <p><img src="/home/v3/static/images/supplierEntry/steptip.png" alt="">所有资质必须清晰可辨，如是复印件请加盖贵司自己的红章。</p>
                    <p><img src="/home/v3/static/images/supplierEntry/steptip.png" alt="">以下所需上传电子版资质仅支持JPG、JPEG、GIF、PNG格式的图片，大小不超过2M。且必须<span>加盖企业红色公章。</span>
                    </p>
                </div>
                <el-form-item prop="certPhoto">
                    <div class="imgmsgs">
                        <div class="companyimg">
                            <p><span>*</span>企业营业执照副本复印件(需加盖红章)<span @click="showimg(0)">查看范本</span></p>
                            <el-upload
                                    class="upload-demo"
                                    action="/home/usr_UsrSupplier_upload"
                                    :file-list="comimg"
                                    :on-success="comsuccess"
                                    :before-upload="beforeAvatarUpload">
                                <div class="result">
                                    <div class="resultname" v-if="basicInfo.certPhotoName">{{basicInfo.certPhotoName}}
                                    </div>
                                    <div class="botton">点击上传</div>
                                </div>
                            </el-upload>
                        </div>
                    </div>
                </el-form-item>
                <el-form-item prop="idCard">
                    <div class="imgmsgs">
                        <div class="companyimg">
                            <p><span></span>法人代表身份证正反面复印件<span
                                    @click="showimg(1)">查看范本</span><br><span>身份证号码</span><input type="text"
                                                                                                v-model="basicInfo.idCard"
                                                                                                placeholder="请输入"></p>
                            <el-upload
                                    class="upload-demo"
                                    action="/home/usr_UsrSupplier_upload"
                                    :file-list="legalimg"
                                    :on-success="legalsuccess"
                                    :before-upload="beforeAvatarUpload">
                                <div class="result">
                                    <div class="resultname" v-if="basicInfo.idCardFrontPhotoName">
                                        {{basicInfo.idCardFrontPhotoName}}
                                    </div>
                                    <div class="botton">点击上传</div>
                                </div>
                            </el-upload>
                        </div>
                    </div>
                </el-form-item>
                <el-form-item prop="operateIdCard">
                    <div class="imgmsgs">
                        <div class="companyimg">
                            <p><span></span>运营人代表身份证正反面复印件<span
                                    @click="showimg(1)">查看范本</span><br><span>身份证号码</span><input type="text"
                                                                                                v-model="basicInfo.operateIdCard"
                                                                                                placeholder="请输入"></p>
                            <el-upload
                                    class="upload-demo"
                                    action="/home/usr_UsrSupplier_upload"
                                    :file-list="operateimg"
                                    :on-success="operatesuccess"
                                    :before-upload="beforeAvatarUpload">
                                <div class="result">
                                    <div class="resultname" v-if="basicInfo.contactsIdCardFrontPhotoName">
                                        {{basicInfo.contactsIdCardFrontPhotoName}}
                                    </div>
                                    <div class="botton">点击上传</div>
                                </div>
                            </el-upload>
                        </div>
                    </div>
                </el-form-item>
            </el-form>
            <div class="bottons">
                <div class="botton botton1" @click="goback">上一步</div>
                <div class="botton botton2" @click="submitimg">提 交</div>
            </div>
        </div>
        <div class="step4" v-if="step == 3">
            <h3 v-if="!state">入驻申请已提交,请等待管理员审核</h3>
            <h3 v-if="state">审核不通过 原因:信息有误 <span style="color: #10389c;;cursor: pointer;" @click="step = 1">修改</span>
            </h3>
        </div>
        <el-dialog
                title="范本"
                :visible.sync="img1show"
                width="80%"
                style="text-align: center;">
            <img style="max-height: 100%;max-width: 100%;" :src="showimgurl" alt="">
        </el-dialog>
    </div>
    <index-bottom></index-bottom>
</div>
<script src="/home/v3/static/js/index-top.js"></script>
<script src="/home/v3/static/js/index-bottom.js"></script>
<script>

    var validateName = function(rule, value, callback){
        let reg = /^[^`~!@#$%^&*()_+?><":\]\[}\{].{0,51}[^`~!@#$%^&*()_+?><":\]\[}\{]$/
        if (!value) {
            return callback(new Error("请填写名称"));
        } else if(!reg.test(value)){
            return callback(new Error("公司名称首尾不能为符号,且长度在1-52位之间"));
        } else {
            callback();
        }
    };
    var validateenglishName = function(rule, value, callback){
        let reg = /^[a-zA-Z ]{1,60}$/
        if (!value) {
            return callback(new Error("请填写英文名称"));
        } else if(!reg.test(value)){
            return callback(new Error("请填写英文字母,且不超过60位"));
        } else {
         callback();
        }
    };
    var validateAddr = function (rule, value, callback) {
        if (!value) {
            return callback(new Error("请填写详细地址"));
        } else {
            callback();
        }
    };
    var validateTel = function (rule, value, callback) {
        let reg = /(^1\d{10}$)|(^(\d{3,4}-)?\d{7,8}$)/
        if (!value) {
            callback();
        } else if (!reg.test(value)) {
            return callback(new Error("请填写正确的手机格式"));
        } else {
            callback();
        }
    };
    var validatePhone = function (rule, value, callback) {
        let reg = /(^1\d{10}$)|(^(\d{3,4}-)?\d{7,8}$)/
        if (!value) {
            return callback(new Error("联系人手机不得为空"));
        } else if (!reg.test(value)) {
            return callback(new Error("请填写正确的手机格式"));
        } else {
            callback();
        }
    };
    var validatePost = function (rule, value, callback) {
        let reg = /^[0-9]{6}$/
        if (!value) {
            callback();
        } else if (!reg.test(value)) {
            return callback(new Error("请填写6位数字"));
        } else {
            callback();
        }
    };
    var validateFax = function (rule, value, callback) {
        let reg = /^(\d{3,4}-)?\d{7,8}$/
        if (!value) {
            callback();
        } else if (!reg.test(value)) {
            return callback(new Error("请填写正确传真格式"));
        } else {
            callback();
        }
    };
    var validatewebsite = function(rule, value, callback){
        let reg = /^http[s]?:\/\/[\w]{1,}.?[\w]{1,}.?[\w/.?&=-]{1,}$/
        if (!value) {
            callback();
        } else if(!reg.test(value)){
            return callback(new Error("请填写正确的网址格式"));
        } else {
             callback();
        }
    };
    var validateRegist = function(rule, value, callback){
        let reg = /^([1-9]\d*|0)(\.\d*[1-9])?$/
        if (!value) {
         callback();
        } else if(!reg.test(value)){
            return callback(new Error("请填写数字,不能以0开头"));
        } else {
            callback();
        }
    };
    var validateEntity = function (rule, value, callback) {
        let reg = /^[\u4e00-\u9fa5]{2,6}$/
        if (!value) {
            callback();
        } else if (!reg.test(value)) {
            return callback(new Error("请填写2至6个中文"));
        } else {
            callback();
        }
    };
    var validateJob = function (rule, value, callback) {
        let reg = /^[A-Za-z\u4e00-\u9fa5]+$/
        if (!value) {
            callback();
        } else if (!reg.test(value)) {
            return callback(new Error("请填写中文或英文"));
        } else {
            callback();
        }
    };
    var validateEmail = function (rule, value, callback) {
        let reg = /^.+@[A-Za-z0-9\.]+\.(cn|com|net)$/
        if (!value) {
            callback();
        } else if (!reg.test(value)) {
            return callback(new Error("请填写正确的邮箱格式"));
        } else {
            callback();
        }
    };
    var validateMarket = function (rule, value, callback) {
        if (value.length == 0) {
            return callback(new Error("请选择目标市场"));
        } else {
            callback();
        }
    };
    var validatePattern = function (rule, value, callback) {
        if (value.length == 0) {
            return callback(new Error("请选择生产模式"));
        } else {
            callback();
        }
    };
    var validateCredit = function (rule, value, callback) {
        let reg = /^[0-9A-Za-z]{18}$/;
        if (!value) {
            return callback(new Error("请填写统一社会信用代码"));
        } else if (!reg.test(value)) {
            return callback(new Error("请填写正确的统一社会信用代码"));
        } else {
            callback();
        }
    };
    var certPhoto = function (rule, value, callback) {
        if (!value) {
            return callback(new Error("请上传营业执照"));
        } else {
            callback();
        }
    };
    var idCard = function (rule, value, callback) {
        let regsfz = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
        if (!value) {
            callback();
        } else if (!regsfz.test(value)) {
            return callback(new Error("请填写正确的身份证号码"));
        } else {
            callback();
        }
    };
    var validatecontacts = function(rule, value, callback){
        let reg = /^[^`~!@#$%^&*()_+?><":\]\[}\{].{0,31}[^`~!@#$%^&*()_+?><":\]\[}\{]$/
        if (!value) {
            callback();
        } else if(!reg.test(value)){
            return callback(new Error("首尾不能为符号,且长度在1-32为之间"));
        } else {
            callback();
        }
    };
    new Vue({
        el: '#shopenter',
        data: {

            state: null,//是否是返回修改
            step: 0,//第(step+1)步

            checkconditiong: false,
            img1show: false,

            showimgurl: '',
            steps: ['开店须知', '填写公司信息', '资质上传', '等待审核'],
            showimgurls: ['/home/v3/static/images/supplierEntry/yyzz.png', '/home/v3/static/images/supplierEntry/sfz.png'],
            //国家集合
            country: [],
            //过滤国家集合
            exhibitionCountry: [],
            exhibitionCountrylength: 0,
            //国家搜索内容
            countrysearch: '',
            //选择国家内容
            selectcountry: [],
            iscountryshow: false,

            //公司,法人,运营者图片
            comimg: [],
            legalimg: [],
            operateimg: [],
            //法人,运营者身份证
            legalsfz: '',
            operatesfz: '',

            // 公司信息
            basicInfo: {
                name: '',
                englishName: '',
                companyType: '',
                companyNature: '',
                companyEstablishTime: '',
                companyAddr: '',
                telephone: '',
                postcode: '',
                fax: '',
                website: '',
                targetedMarket: [],
                annualProduction: '',
                prodPattern: [],
                creditCode: '',
                registeredCapital: '',
                entity: '',
                businessLicenseBeginTime: '',
                businessLicenseEndTime: '',
                businessLicenseIsSecular: '0',
                taxpayerType: '',
                contacts: '',
                department: '',
                jobTitle: '',
                phone: '',
                contactEmail: '',
                certPhoto: '',
                certPhotoName: '',
                idCardFrontPhoto: '',
                idCardFrontPhotoName: '',
                idCard: '',
                contactsIdCardFrontPhoto: '',
                contactsIdCardFrontPhotoName: '',
                operateIdCard: '',
                applicationTime: '',
                purchasePkey: 9999,
                lang: 'zh_CN',
                userid: null
            },
            //用户信息
            usermsg: {},
            pkey: null,
            lang: null,
            storeStatus: null,

            //验证
            firstform: {
                name: [
                    {validator: validateName, trigger: 'blur'}
                ],
                englishName: [
                    {validator: validateenglishName, trigger: 'blur'}
                ],
                companyAddr: [
                    {validator: validateAddr, trigger: 'blur'}
                ],
                telephone: [
                    {validator: validateTel, trigger: 'blur'}
                ],
                postcode: [
                    {validator: validatePost, trigger: 'blur'}
                ],
                fax: [
                    {validator: validateFax, trigger: 'blur'}
                ],
                website:[
                    { validator: validatewebsite, trigger: 'blur' }
                ],
                annualProduction:[
                    { validator: validateRegist, trigger: 'change' }
                ],
                registeredCapital: [
                    {validator: validateRegist, trigger: 'blur'}
                ],
                entity: [
                    {validator: validateEntity, trigger: 'blur'}
                ],
                contacts:[
                    { validator: validatecontacts, trigger: 'blur' }
                ],
                department: [
                    {validator: validatecontacts, trigger: 'blur'}
                ],
                jobTitle: [
                    {validator: validatecontacts, trigger: 'blur'}
                ],
                phone:[
                    { validator: validatePhone, trigger: 'blur' }
                ],
                contactEmail: [
                    {validator: validateEmail, trigger: 'blur'}
                ],
                targetedMarket: [
                    {validator: validateMarket, trigger: 'change'}
                ],
                prodPattern: [
                    {validator: validatePattern, trigger: 'change'}
                ],
                creditCode: [
                    {validator: validateCredit, trigger: 'blur'}
                ]
            },
            secondform: {
                certPhoto: [
                    {validator: certPhoto, trigger: 'blur'}
                ],
                idCard: [
                    {validator: idCard, trigger: 'blur'}
                ],
                operateIdCard: [
                    {validator: idCard, trigger: 'blur'}
                ]
            }
        },
        mounted() {
            let date = new Date();
            this.basicInfo.applicationTime = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
            this.getUsermsg();
        },
        methods: {
            //获取信息,判断是否修改
            getRetype: function (val) {
                let self = this;
                axios.get('/home/usr_UsrSupplier_loadOnlineSup', {
                    params: {
                        purchasePkey: self.basicInfo.userid,
                    }
                }).then(function (res) {
                    let data = res.data
                    if (data.status == null) {
                         self.getMainmsg();
                        return false;
                    } else if (data.status == 0) {
                        self.step = 3;
                    } else if (data.status == 1 && data.storeStatus == 1) {
                        window.location.href = '/newseller/'
                    } else if (data.status == 2) {
                        self.state = true;
                        self.step = 3;
                    }

                    //需要处理的数据,先处理
                    self.pkey = data.pkey
                    self.storeStatus = data['storeStatus']
                    data['prodPattern'] = data['prodPattern'] ? data['prodPattern'].split(' / ') : []
                    data['userid'] = parseInt(data['userid'].split('##')[0])
                    data['certPhotoName'] = data['annex'].cert_photo_name
                    data['idCardFrontPhotoName'] = data['annex'].    id_card_front_photo_name
                    data['contactsIdCardFrontPhotoName'] = data['annex'].contacts_id_card_front_photo_name
                    data['businessLicenseIsSecular'] = data['businessLicenseIsSecular'] + ''
                    if (data['businessLicenseIsSecular'] == '1') {
                        self.businessLicenseEndTime = '';
                    }
                    data['targetedMarket'] = data['targetedMarket'] ? data['targetedMarket'].split(',') : []
                    for (let i in data['targetedMarket']) {
                        data['targetedMarket'][i] = parseInt(data['targetedMarket'][i])
                    }
                    for (let i in self.basicInfo) {
                        for (let j in data) {
                            if (j == i) {
                                self.basicInfo[j] = data[i]
                            }
                        }
                    }
                    self.selectshow();
                })
            },
            //获取个人信息
            getUsermsg: function () {
                let self = this;
                axios.get('/home/plt_PltConfig_getSysConfig')
                    .then(function (res) {
                        if (res.data.ret == 1) {
                            if(res.data.result.user){
                                self.$set(self,'usermsg',res.data.result.user)
                                self.basicInfo.userid = res.data.result.user.id
                                self.getcountry();
                            }else{
                                // self.showmsg('请先登录,3秒后跳转至登录页面')
                                // setTimeout(function(){
                                //     window.location.href='/home/usr_UsrPurchase_sign'
                                // },3000)
                                util_function_obj.alertWhenNoLogin(self);
                            }
                        }else{
                            self.$message({
                                message: res.data.msg || '获取个人信息失败',
                                type: 'warning'
                            });
                        }
                    }).catch(function(err){
                        self.$message.error(err || 'Network error,please try again later');
                    })
            },
            //获取公司信息
            getMainmsg:function(){
                let self = this;
                axios.get('/home/usr_UsrMain_getRegistById',{
                    params:{
                         id:self.basicInfo.userid
                    }
                }).then(function(res){
                    if(res.data.ret == 1){
                        let data = res.data.result[0];
                        if(data.company){
                            self.basicInfo.name = data.company;
                        }
                        if(data.address){
                            self.basicInfo.companyAddr = data.address;
                         }
                        if(data.contacts){
                            self.basicInfo.contacts = data.contacts;
                        }
                        if(data.telphone){
                            self.basicInfo.phone = data.telphone;
                        }
                    }else{
                        self.$message({
                            message: res.data.msg || "获取公司信息失败，请稍后再试",
                            type: 'warning'
                        });
                    }
                }).catch(function(err){
                    self.$message.error(err || 'Network error,please try again later');
                })
            },
            //获取国家
            getcountry: function () {
                var self = this
                axios.get('/home/plt_PltCountry_list', {
                    params: {
                        lang: self.basicInfo.lang
                    }
                }).then(function (res) {
                    if(res.data.ret == 1){
                        self.$set(self, 'country', res.data.result)
                        self.$set(self, 'exhibitionCountry', res.data.result)
                        self.exhibitionCountrylength = Math.ceil(self.exhibitionCountry.length / 3)
                        self.getRetype();
                    }else{
                        self.$message({
                            message: res.data.msg || '获取国家列表失败',
                            type: 'warning'
                        });
                    }
                }).catch(function(err){
                    self.$message.error(err || 'Network error,please try again later');
                })
            },
            //承认条约
            agree: function () {
                if (this.checkconditiong) {
                    this.step += 1;
                }
            },
            //范本图片展示
            showimg: function (val) {
                this.img1show = true
                this.showimgurl = this.showimgurls[val]
            },
            //上传图片回传
            comsuccess:function(response, file, fileList){
                if(response.ret == 1){
                    this.basicInfo.certPhotoName = response.result.originalName;
                    this.basicInfo.certPhoto = response.result.url;
                }else{
                    this.$message({
                        message: response.msg || '图片上传失败',
                        type: 'warning'
                    });
                }
            },
            legalsuccess:function(response, file, fileList){
                if(response.ret == 1){
                    this.basicInfo.idCardFrontPhotoName = response.result.originalName;
                    this.basicInfo.idCardFrontPhoto = response.result.url;
                }else{
                    this.$message({
                        message: response.msg ||  '图片上传失败',
                        type: 'warning'
                    });
                }
            },
            operatesuccess:function(response, file, fileList){
                if(response.ret == 1){
                    this.basicInfo.contactsIdCardFrontPhotoName = response.result.originalName;
                    this.basicInfo.contactsIdCardFrontPhoto = response.result.url;
                }else{
                    this.$message({
                        message: response.msg ||  '图片上传失败',
                        type: 'warning'
                    });
                }
            },
            //营业执照,身份证的验证,和最后提交
            submitimg: function () {
                let self = this;
                this.$refs['secondbasicInfo'].validate((vaild) => {
                    if (!vaild) {
                        self.$alert('请检查表单', {
                            customClass: "my-custom-element-alert-class fs-content-18",
                            center: true,
                            closeOnClickModal: true,
                            showConfirmButton: false
                        }).catch(() => {
                        });
                        return false
                    } else {
                        self.$confirm('是否提交?', '提示', {
                            customClass: "my-custom-element-alert-class fs-content-18",
                            confirmButtonText: '确定',
                            cancelButtonText: '取消',
                            type: 'info ',
                            center: true
                        }).then(() => {
                            let data = {};
                            for (let i in self.basicInfo) {
                                if (i == 'certPhotoName' || i == 'idCardFrontPhotoName' || i == 'contactsIdCardFrontPhotoName' || i == 'purchasePkey' || i == 'lang') {
                                    data[i] = self.basicInfo[i]
                                } else {
                                    data['bean.' + i] = self.basicInfo[i]
                                }
                            }
                            data['bean.targetedMarket'] = data['bean.targetedMarket'].join(',')
                            data['bean.prodPattern'] = data['bean.prodPattern'].join('/')
                            if (data['bean.businessLicenseIsSecular'] == '1') {
                                data['bean.businessLicenseEndTime'] = '';
                            }
                            for (let i in data) {
                                if (data[i] == '') {
                                    data[i] = null
                                }
                            }
                            if (self.state) {
                                self.retypeSubmit(data)
                            } else {
                                self.firstSubmit(data)
                            }
                        }).catch(() => {
                            self.$message({
                                message: '提交取消',
                                type: 'warning'
                            });
                        })
                    }
                })
            },
            //首次提交
            firstSubmit: function (data) {
                let self = this
                axios.post('/home/usr_UsrSupplier_insInfo',
                    Qs.stringify(data)
                ).then(function (res) {
                    if (res.data.ret == 1) {
                        self.showmsg('提交成功')
                        setTimeout(function () {
                            self.step += 1;
                        }, 1000)
                    } else {
                        self.$message({
                            message: res.data.msg || '提交失败',
                            type: 'warning'
                        });
                    }
                }).catch(function (error) {
                    self.$message.error(error || 'Network error,please try again later');
                })
            },
            //修改提交
            retypeSubmit: function (data) {
                let self = this
                data['bean.pkey'] = self.pkey
                data['bean.storeStatus'] = self.storeStatus
                axios.post('/home/usr_UsrSupplier_updInfo',
                    Qs.stringify(data)
                ).then(function (res) {
                    if (res.data.ret == 1) {
                        self.state = false;
                        self.showmsg('提交成功')
                        setTimeout(function () {
                            self.step += 1;
                        }, 1000)
                    } else {
                        self.$message({
                            message: res.data.msg || '提交失败',
                            type: 'warning'
                        });
                    }
                }).catch(function (error) {
                    self.$message.error(error || 'Network error,please try again later');
                })
            },
            //表单信息验证
            formSubmit: function () {
                let self = this;
                var begin = Date.parse(new Date(this.basicInfo.businessLicenseBeginTime))
                var end = Date.parse(new Date(this.basicInfo.businessLicenseEndTime))
                if (begin > end && this.basicInfo.businessLicenseIsSecular === '0') {
                    this.$message({
                        message: '营业执照结束时间，不能小于开始时间',
                        type: 'warning',
                        center: true
                    })
                    return false
                }
                this.$refs['basicInfo'].validate((vaild) => {
                    if (!vaild) {
                        self.$alert('请检查表单', {
                            customClass: "my-custom-element-alert-class fs-content-18",
                            closeOnClickModal: true,
                            showConfirmButton: false
                        }).catch(() => {
                        });
                        return false;
                    } else {
                        self.step += 1;
                    }
                })
            },
            //上一步
            goback: function (val) {
                this.step -= 1;
            },
            //搜索国家
            filterCountry: function (val) {
                var reg = new RegExp("^" + val, "i")
                this.$set(this, 'exhibitionCountry', this.country)
                let option = [];
                let k = 0;
                for (let i  in this.exhibitionCountry) {
                    if (reg.test(this.exhibitionCountry[i].name)) {
                        option[k] = this.exhibitionCountry[i];
                        k++;
                    }
                }
                this.$set(this, 'exhibitionCountry', option)
            },
            //消息提示
            showmsg: function (val) {
                this.$alert(val, {
                    closeOnClickModal: true,
                    showConfirmButton: false,
                    customClass: "my-custom-element-alert-class fs-content-18",
                }).catch(() => {
                });
            },
            //判断图片
            beforeAvatarUpload: function (res) {
                const isJPG = res.type === 'image/jpeg';
                const isPNG = res.type === 'image/png';
                const isGIF = res.type === 'image/gif';
                const isLt2M = res.size / 1024 / 1024 < 2;

                if (!isJPG && !isPNG && !isGIF) {
                    this.$message.error('图片只能是 JPG ,GIF ,PNG格式!');
                }
                if (!isLt2M) {
                    this.$message.error('图片大小不能超过 2MB!');
                }
                return (isJPG || isPNG || isGIF) && isLt2M;
            },
            //转换大写
            toUpper: function () {
                this.basicInfo.creditCode = this.basicInfo.creditCode.toUpperCase();
            },
            //选择国家显示
            selectshow: function () {
                this.selectcountry = []
                for (let i in this.basicInfo.targetedMarket) {
                    for (let j in this.country) {
                        if (this.basicInfo.targetedMarket[i] == this.country[j].id) {
                            this.selectcountry.push(this.country[j])
                        }
                    }
                }
            },
            //删除所选国家
            deletecountry: function (index) {
                this.basicInfo.targetedMarket.splice(index, 1)
                this.selectcountry.splice(index, 1)
            },
            //内部国家选择框
            showselectcon: function (val) {
                this.iscountryshow = !this.iscountryshow;
            }
        }
    })
</script>
</body>
</html>
