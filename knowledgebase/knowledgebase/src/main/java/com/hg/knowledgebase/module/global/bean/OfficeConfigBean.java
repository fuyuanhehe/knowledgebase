package com.hg.knowledgebase.module.global.bean;

/**
 * 
 * @Description: office Config对象
 * @Author: 况克冬
 * @CreateDate: 2020年3月11日上午11:23:58
 * @Version: 1.0.0
 */
public class OfficeConfigBean
{

    /**
     * 文档宽度(默认100%)
     */
    private String width = "100%";

    /**
     * 文档高度(默认100%)
     */
    private String height = "100%";

    /**
     * 文档类型
     */
    private String documentType;

    /**
     * 文档aip地址
     */
    private String apiUrl;

    /**
     * 文档信息
     */
    private Document document;

    /**
     * 文档编辑配置
     */
    private EditorConfig editorConfig;

    /**
     * 用户对象
     */
    private User user;

    public User getUser()
    {
        return user == null ? this.new User() : user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public String getApiUrl()
    {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl)
    {
        this.apiUrl = apiUrl;
    }

    public String getWidth()
    {
        return width;
    }

    public void setWidth(String width)
    {
        this.width = width;
    }

    public String getHeight()
    {
        return height;
    }

    public void setHeight(String height)
    {
        this.height = height;
    }

    public String getDocumentType()
    {
        return documentType;
    }

    public void setDocumentType(String documentType)
    {
        this.documentType = documentType;
    }

    public Document getDocument()
    {
        return document == null ? this.new Document() : document;
    }

    public void setDocument(Document document)
    {
        this.document = document;
    }

    public EditorConfig getEditorConfig()
    {
        return editorConfig == null ? new EditorConfig() : editorConfig;
    }

    public void setEditorConfig(EditorConfig editorConfig)
    {
        this.editorConfig = editorConfig;
    }

    /**
     * 
     * @Description: 用户对象
     * @Author: 况克冬
     * @CreateDate: 2020年3月12日下午12:10:53
     * @Version: 1.0.0
     */
    public class User
    {
        /**
         * 用户ID
         */
        public String id;

        /**
         * 用户名称
         */
        public String name;

        public String getId()
        {
            return id;
        }

        public void setId(String id)
        {
            this.id = id;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }
    }

    /**
     * 
     * @Description: 文档编辑配置对象
     * @Author: 况克冬
     * @CreateDate: 2020年3月11日上午11:32:58
     * @Version: 1.0.0
     */
    public class EditorConfig
    {
        /**
         * 编辑回调地址
         */
        private String callbackUrl;

        /**
         * 操作模式（view：显示模式；edit：编辑模式）
         */
        private String mode;

        /**
         * 语言(默认中文)
         */
        private String lang = "zh-CN";

        /**
         * embedded部分仅用于embedded文档类型（请参阅config部分了解如何定义embedded文档类型）。它允许更改在嵌入式模式下定义按钮行为的设置。
         * The embedded section is for the embedded document type only (see the
         * config section to find out how to define the embedded document type).
         * It allows to change the settings which define the behavior of the
         * buttons in the embedded mode.
         */
        private Embedded embedded;

        /**
         * 客服信息
         */
        private Customization customization;

        /**
         * 插件部分允许将特殊的插件连接到您的文档服务器安装，这将帮助您向文档编辑器添加其他功能。 The plugins section
         * allows to connect the special add-ons to your Document Server
         * installation which will help you add additional features to document
         * editors.
         */
        private Plugins plugins;

        public Plugins getPlugins()
        {
            return plugins == null ? this.new Plugins() : plugins;
        }

        public void setPlugins(Plugins plugins)
        {
            this.plugins = plugins;
        }

        public Embedded getEmbedded()
        {
            return embedded == null ? this.new Embedded() : embedded;
        }

        public void setEmbedded(Embedded embedded)
        {
            this.embedded = embedded;
        }

        public Customization getCustomization()
        {
            return customization == null ? this.new Customization()
                    : customization;
        }

        public void setCustomization(Customization customization)
        {
            this.customization = customization;
        }

        public String getLang()
        {
            return lang;
        }

        public void setLang(String lang)
        {
            this.lang = lang;
        }

        public String getMode()
        {
            return mode;
        }

        public void setMode(String mode)
        {
            this.mode = mode;
        }

        public String getCallbackUrl()
        {
            return callbackUrl;
        }

        public void setCallbackUrl(String callbackUrl)
        {
            this.callbackUrl = callbackUrl;
        }

        /**
         * 
         * @Description: embedded部分仅用于embedded文档类型（请参阅config部分了解如何定义embedded文档类型）。它允许更改在嵌入式模式下定义按钮行为的设置
         *               The embedded section is for the embedded document type
         *               only (see the config section to find out how to define
         *               the embedded document type). It allows to change the
         *               settings which define the behavior of the buttons in
         *               the embedded mode.
         * @Author: 况克冬
         * @CreateDate: 2020年3月13日下午2:08:13
         * @Version: 1.0.0
         */
        public class Embedded
        {
            /**
             * 定义允许将文档保存到用户个人计算机上的绝对URL。 Defines the absolute URL that will
             * allow the document to be saved onto the user personal computer.
             */
            private String saveUrl;

            /**
             * 定义作为嵌入到网页中的文档的源文件的文档的绝对URL。 Defines the absolute URL to the
             * document serving as a source file for the document embedded into
             * the web page.
             */
            private String embedUrl;

            /**
             * 定义将以全屏模式打开的文档的绝对URL。 Defines the absolute URL to the document
             * which will open in full screen mode.
             */
            private String fullscreenUrl;

            /**
             * 定义允许其他用户共享此文档的绝对URL。 Defines the absolute URL that will allow
             * other users to share this document.
             */
            private String shareUrl;

            /**
             * 定义嵌入查看器工具栏的位置，可以是顶部或底部。 Defines the place for the embedded viewer
             * toolbar, can be either top or bottom.
             */
            private String toolbarDocked = "top";

            public String getFullscreenUrl()
            {
                return fullscreenUrl;
            }

            public void setFullscreenUrl(String fullscreenUrl)
            {
                this.fullscreenUrl = fullscreenUrl;
            }

            public String getSaveUrl()
            {
                return saveUrl;
            }

            public void setSaveUrl(String saveUrl)
            {
                this.saveUrl = saveUrl;
            }

            public String getEmbedUrl()
            {
                return embedUrl;
            }

            public void setEmbedUrl(String embedUrl)
            {
                this.embedUrl = embedUrl;
            }

            public String getShareUrl()
            {
                return shareUrl;
            }

            public void setShareUrl(String shareUrl)
            {
                this.shareUrl = shareUrl;
            }

            public String getToolbarDocked()
            {
                return toolbarDocked;
            }

            public void setToolbarDocked(String toolbarDocked)
            {
                this.toolbarDocked = toolbarDocked;
            }
        }

        /**
         * 
         * @Description: 自定义部分允许自定义编辑器界面，使其看起来像其他产品（如果有），并更改是否存在其他按钮、链接、更改徽标和编辑器所有者详细信息。
         *               The customization section allows to customize the
         *               editor interface so that it looked like your other
         *               products (if there are any) and change the presence or
         *               absence of the additional buttons, links, change logos
         *               and editor owner details.
         * @Author: 况克冬
         * @CreateDate: 2020年3月13日下午2:17:53
         * @Version: 1.0.0
         */
        public class Customization
        {
            /**
             * 定义是否启用或禁用“自动保存”菜单选项。如果设置为false，则只能选择严格的协同编辑模式，因为没有自动保存，Fast不起作用。
             * 请注意，如果在菜单中更改此选项，它将保存到浏览器的本地存储中。默认值为true。 Defines if the Autosave
             * menu option is enabled or disabled. If set to false, only Strict
             * co-editing mode can be selected, as Fast does not work without
             * autosave. Please note that in case you change this option in menu
             * it will be saved to your browser localStorage. The default value
             * is true.
             */
            private boolean autosave = true;

            /**
             * 定义是否显示或隐藏聊天菜单按钮；请注意，如果隐藏聊天按钮，相应的聊天功能也将被禁用。默认值为true。 Defines if
             * the Chat menu button is displayed or hidden; please note that in
             * case you hide the Chat button, the corresponding chat
             * functionality will also be disabled. The default value is true.
             */
            private boolean chat;

            /**
             * 定义用户是否只能编辑和删除其注释。默认值为false。 Defines if the user can edit and
             * delete only his comments. The default value is false.
             */
            private boolean commentAuthorOnly;

            /**
             * 定义是否显示或隐藏“注释”菜单按钮；请注意，如果隐藏“注释”按钮，则相应的注释功能将仅可用于查看，注释的添加和编辑将不可用。默认值为true。
             * Defines if the Comments menu button is displayed or hidden;
             * please note that in case you hide the Comments button, the
             * corresponding commenting functionality will be available for
             * viewing only, the adding and editing of comments will be
             * unavailable. The default value is true.
             */
            private boolean comments;

            /**
             * 定义附加操作按钮是显示在徽标旁边的编辑器窗口标题的上部（false）中，还是显示在使标题更紧凑的工具栏（true）中。默认值为false
             * Defines if the additional action buttons are displayed in the
             * upper part of the editor window header next to the logo (false)
             * or in the toolbar (true) making the header more compact. The
             * default value is false.
             */
            private boolean compactHeader;

            /**
             * 定义是否显示或隐藏“注释”菜单按钮；请注意，如果隐藏“注释”按钮，则相应的注释功能将仅可用于查看，注释的添加和编辑将不可用。默认值为true。
             * Defines if the top toolbar type displayed is full (false) or
             * compact true. The default value is false.
             */
            private boolean compactToolbar;

            /**
             * 包含将在编辑器的“关于”部分中显示并对所有编辑器用户可见的信息。对象具有以下参数 Contains the information
             * which will be displayed int the editor About section and visible
             * to all the editor users. The object has the following parameters:
             */
            private Customer customer;

            /**
             * 定义“反馈和支持”菜单按钮的设置。可以是boolean（只显示或隐藏Feedback&Support菜单按钮）或object。在对象类型的情况下，以下参数可用
             * Defines settings for the Feedback & Support menu button. Can be
             * either boolean (simply displays or hides the Feedback & Support
             * menu button) or object. In case of object type the following
             * parameters are available
             */
            // private Feedback feedback;
            private boolean feedback;

            /**
             * 定义“打开文件位置”菜单按钮和右上角按钮的设置。对象具有以下参数 Defines settings for the Open
             * file location menu button and upper right corner button. The
             * object has the following parameters
             */
            // private Goback goback;
            private boolean goback;

            /**
             * 定义“帮助”菜单按钮是显示还是隐藏。默认值为true Defines if the Help menu button is
             * displayed or hidden. The default value is true
             */
            private boolean help;

            /**
             * 定义第一次加载时是否显示或隐藏右菜单。默认值为false Defines if the right menu is
             * displayed or hidden on first loading. The default value is false.
             */
            private boolean hideRightMenu;

            /**
             * 更改编辑器标题左上角的图像文件。建议的图像高度为20像素 Changes the image file at the top
             * left corner of the Editor header. The recommended image height is
             * 20 pixels. The object has the following parameters:
             */
            private Logo logo;

            /**
             * 定义在注释中提及后描述事件的提示。如果为true，则提示指示用户将收到通知并访问文档。如果为false，则提示指示用户将仅收到提及通知。默认值为true。
             * Defines the hint that describes the event after mentions in a
             * comment. If true, a hint indicates that the user will receive a
             * notification and access to the document. If false, a hint
             * indicates that the user will receive only a notification of the
             * mention. The default value is true.
             */
            private boolean mentionShare = true;

            /**
             * 定义打开文档进行查看时将使用的审阅编辑模式。只有当mode参数设置为view时，它才可用于文档编辑器。可以采用以下值：(final、markup、original)
             * Defines the review editing mode which will be used when the
             * document is opened for viewing. It will only be available for the
             * document editor if the mode parameter is set to view. Can take
             * the following values:
             */
            private String reviewDisplay = "original";

            /**
             * 定义加载编辑器时是否自动显示或隐藏“审阅更改”面板。默认值为false。 Defines if the review
             * changes panel is automatically displayed or hidden when the
             * editor is loaded. The default value is false.
             */
            private boolean showReviewChanges;

            /**
             * 定义加载编辑器时是否自动打开或关闭拼写检查器。拼写检查器只对文档编辑器和演示文稿编辑器可用。默认值为true。 Defines
             * if the spell checker is automatically switched on or off when the
             * editor is loaded. Spell checker will only be available for the
             * document editor and the presentation editor. The default value is
             * true.
             */
            private boolean spellcheck = true;

            /**
             * 定义顶部工具栏选项卡是清晰显示（设置为false时）还是仅高亮显示以查看选中的选项卡（设置为true时）。默认值为false。
             * Defines if the top toolbar tabs are distinctly displayed (when
             * set to false) or only highlighted to see which one is selected
             * (when set to true). The default value is false.
             */
            private boolean toolbarNoTabs = true;

            /**
             * 定义标尺和对话框中使用的测量单位。可以采用以下值：(cm、pt、inch) Defines the measurement
             * units used on the ruler and in dialog boxes. Can take the
             * following values: cm - centimeters, pt - points, inch - inches.
             * The default value is centimeters (cm).
             */
            private String unit = "cm";

            /**
             * 定义以百分比度量的文档显示缩放值。可以采用大于0的值。对于文本文档和演示文稿，可以将此参数设置为-1（使文档适合页面选项）或-2（使文档页面宽度适合编辑器页面）。默认值为100。
             * Defines the document display zoom value measured in percent. Can
             * take values larger than 0. For text documents and presentations
             * it is possible to set this parameter to -1 (fitting the document
             * to page option) or to -2 (fitting the document page width to the
             * editor page). The default value is 100.
             */
            private int zoom = 100;

            private boolean width = true;

            private boolean page = true;

            public boolean isWidth()
            {
                return width;
            }

            public void setWidth(boolean width)
            {
                this.width = width;
            }

            public boolean isPage()
            {
                return page;
            }

            public void setPage(boolean page)
            {
                this.page = page;
            }

            public boolean isAutosave()
            {
                return autosave;
            }

            public void setAutosave(boolean autosave)
            {
                this.autosave = autosave;
            }

            public boolean isChat()
            {
                return chat;
            }

            public void setChat(boolean chat)
            {
                this.chat = chat;
            }

            public boolean isCommentAuthorOnly()
            {
                return commentAuthorOnly;
            }

            public void setCommentAuthorOnly(boolean commentAuthorOnly)
            {
                this.commentAuthorOnly = commentAuthorOnly;
            }

            public boolean isComments()
            {
                return comments;
            }

            public void setComments(boolean comments)
            {
                this.comments = comments;
            }

            public boolean isCompactHeader()
            {
                return compactHeader;
            }

            public void setCompactHeader(boolean compactHeader)
            {
                this.compactHeader = compactHeader;
            }

            public boolean isCompactToolbar()
            {
                return compactToolbar;
            }

            public void setCompactToolbar(boolean compactToolbar)
            {
                this.compactToolbar = compactToolbar;
            }

            public Customer getCustomer()
            {
                return customer == null ? this.new Customer() : customer;
            }

            public void setCustomer(Customer customer)
            {
                this.customer = customer;
            }

            /*
             * public Feedback getFeedback() { return feedback == null ?
             * this.new Feedback() : feedback; }
             * 
             * public void setFeedback(Feedback feedback) { this.feedback =
             * feedback; }
             */
            public boolean getFeedback()
            {
                return feedback;
            }

            public void setFeedback(boolean feedback)
            {
                this.feedback = feedback;
            }

            public boolean isHelp()
            {
                return help;
            }

            public void setHelp(boolean help)
            {
                this.help = help;
            }

            public boolean isHideRightMenu()
            {
                return hideRightMenu;
            }

            public void setHideRightMenu(boolean hideRightMenu)
            {
                this.hideRightMenu = hideRightMenu;
            }

            public Logo getLogo()
            {
                return logo == null ? this.new Logo() : logo;
            }

            public void setLogo(Logo logo)
            {
                this.logo = logo;
            }

            public boolean isMentionShare()
            {
                return mentionShare;
            }

            public void setMentionShare(boolean mentionShare)
            {
                this.mentionShare = mentionShare;
            }

            public String getReviewDisplay()
            {
                return reviewDisplay;
            }

            public void setReviewDisplay(String reviewDisplay)
            {
                this.reviewDisplay = reviewDisplay;
            }

            public boolean isShowReviewChanges()
            {
                return showReviewChanges;
            }

            public void setShowReviewChanges(boolean showReviewChanges)
            {
                this.showReviewChanges = showReviewChanges;
            }

            public boolean isSpellcheck()
            {
                return spellcheck;
            }

            public void setSpellcheck(boolean spellcheck)
            {
                this.spellcheck = spellcheck;
            }

            public boolean isToolbarNoTabs()
            {
                return toolbarNoTabs;
            }

            public void setToolbarNoTabs(boolean toolbarNoTabs)
            {
                this.toolbarNoTabs = toolbarNoTabs;
            }

            public String getUnit()
            {
                return unit;
            }

            public void setUnit(String unit)
            {
                this.unit = unit;
            }

            public int getZoom()
            {
                return zoom;
            }

            public void setZoom(int zoom)
            {
                this.zoom = zoom;
            }

            /*
             * public Goback getGoback() { return goback == null ? this.new
             * Goback() : goback; }
             * 
             * public void setGoback(Goback goback) { this.goback = goback; }
             */
            public boolean getGoback()
            {
                return goback;
            }

            public void setGoback(boolean goback)
            {
                this.goback = goback;
            }

            /**
             * 
             * @Description: 定义“打开文件位置”菜单按钮和右上角按钮的设置。对象具有以下参数 Defines settings
             *               for the Open file location menu button and upper
             *               right corner button. The object has the following
             *               parameters
             * @Author: 况克冬
             * @CreateDate: 2020年3月13日下午2:05:55
             * @Version: 1.0.0
             */
            public class Goback
            {
                private boolean blank = true;

                private boolean requestClose;

                private String text;

                private String url;

                public boolean isBlank()
                {
                    return blank;
                }

                public void setBlank(boolean blank)
                {
                    this.blank = blank;
                }

                public boolean isRequestClose()
                {
                    return requestClose;
                }

                public void setRequestClose(boolean requestClose)
                {
                    this.requestClose = requestClose;
                }

                public String getText()
                {
                    return text;
                }

                public void setText(String text)
                {
                    this.text = text;
                }

                public String getUrl()
                {
                    return url;
                }

                public void setUrl(String url)
                {
                    this.url = url;
                }
            }

            /**
             * 
             * @Description: 定义“反馈和支持”菜单按钮的设置。可以是boolean（只显示或隐藏Feedback&Support菜单按钮）或object。在对象类型的情况下，以下参数可用
             *               Defines settings for the Feedback & Support menu
             *               button. Can be either boolean (simply displays or
             *               hides the Feedback & Support menu button) or
             *               object. In case of object type the following
             *               parameters are available
             * @Author: 况克冬
             * @CreateDate: 2020年3月13日下午1:51:07
             * @Version: 1.0.0
             */
            public class Feedback
            {
                private String url;

                private boolean visible = true;

                public boolean isVisible()
                {
                    return visible;
                }

                public void setVisible(boolean visible)
                {
                    this.visible = visible;
                }

                public String getUrl()
                {
                    return url;
                }

                public void setUrl(String url)
                {
                    this.url = url;
                }
            }

            /**
             * 
             * @Description: 包含将在编辑器的“关于”部分中显示并对所有编辑器用户可见的信息 Contains the
             *               information which will be displayed int the editor
             *               About section and visible to all the editor users.
             *               The object has the following parameters:
             * @Author: 况克冬
             * @CreateDate: 2020年3月13日上午11:49:09
             * @Version: 1.0.0
             */
            public class Customer
            {
                /**
                 * 访问编辑或编辑作者的公司或个人的邮政地址 postal address of the company or person
                 * who gives access to the editors or the editor authors,
                 */
                private String address;

                /**
                 * 关于你想让其他人知道的公司或个人的一些附加信息 some additional information about the
                 * company or person you want the others to know
                 */
                private String info;

                /**
                 * 图像徽标的路径（对于这个文件没有特别的建议，但是如果是透明背景的.png格式会更好）。图像必须具有以下大小：432x70
                 * the path to the image logo (there are no special
                 * recommendations for this file, but it would be better if it
                 * were in .png format with transparent background). The image
                 * must have the following size: 432x70
                 */
                private String logo;

                /**
                 * 访问编辑或编辑作者的公司或个人的电子邮件地址 email address of the company or person
                 * who gives access to the editors or the editor authors
                 */
                private String mail;

                /**
                 * 访问编辑或编辑作者的公司或个人的名称 the name of the company or person who
                 * gives access to the editors or the editor authors
                 */
                private String name;

                /**
                 * 上述公司或个人的家庭网站地址 home website address of the above company or
                 * person
                 */
                private String www;

                public String getAddress()
                {
                    return address;
                }

                public void setAddress(String address)
                {
                    this.address = address;
                }

                public String getInfo()
                {
                    return info;
                }

                public void setInfo(String info)
                {
                    this.info = info;
                }

                public String getLogo()
                {
                    return logo;
                }

                public void setLogo(String logo)
                {
                    this.logo = logo;
                }

                public String getMail()
                {
                    return mail;
                }

                public void setMail(String mail)
                {
                    this.mail = mail;
                }

                public String getName()
                {
                    return name;
                }

                public void setName(String name)
                {
                    this.name = name;
                }

                public String getWww()
                {
                    return www;
                }

                public void setWww(String www)
                {
                    this.www = www;
                }
            }

            /**
             * 
             * @Description: 更改编辑器标题左上角的图像文件。建议的图像高度为20像素 Changes the image file
             *               at the top left corner of the Editor header. The
             *               recommended image height is 20 pixels. The object
             *               has the following parameters:
             * @Author: 况克冬
             * @CreateDate: 2020年3月13日下午1:36:34
             * @Version: 1.0.0
             */
            public class Logo
            {
                /**
                 * 用于在通用工作模式下显示的图像文件的路径（即，在所有编辑器的视图和编辑模式下）。图像必须具有以下大小：172x40
                 * path to the image file used to show in common work mode (i.e.
                 * in view and edit modes for all editors). The image must have
                 * the following size: 172x40
                 */
                private String image;

                /**
                 * 用于在嵌入模式下显示的图像文件的路径（请参阅配置部分以了解如何定义嵌入的文档类型）。图像必须具有以下大小：248x40，
                 * path to the image file used to show in the embedded mode (see
                 * the config section to find out how to define the embedded
                 * document type). The image must have the following size:
                 * 248x40,
                 */
                private String imageEmbedded;

                /**
                 * 当有人点击徽标图像时将使用的绝对URL（可用于转到您的网站等）。保留为空字符串或空以使徽标不可单击， the
                 * absolute URL which will be used when someone clicks the logo
                 * image (can be used to go to your web site, etc.). Leave as an
                 * empty string or null to make the logo not clickable,
                 */
                private String url;

                public String getImage()
                {
                    return image;
                }

                public void setImage(String image)
                {
                    this.image = image;
                }

                public String getImageEmbedded()
                {
                    return imageEmbedded;
                }

                public void setImageEmbedded(String imageEmbedded)
                {
                    this.imageEmbedded = imageEmbedded;
                }

                public String getUrl()
                {
                    return url;
                }

                public void setUrl(String url)
                {
                    this.url = url;
                }
            }
        }

        /**
         * 
         * @Description: 插件部分允许将特殊的插件连接到您的文档服务器安装，这将帮助您向文档编辑器添加其他功能。 The plugins
         *               section allows to connect the special add-ons to your
         *               Document Server installation which will help you add
         *               additional features to document editors.
         * @Author: 况克冬
         * @CreateDate: 2020年3月13日下午2:14:37
         * @Version: 1.0.0
         */
        public class Plugins
        {
            /**
             * 定义插件的标识符数组（在config.json中输入），当编辑器打开时，该数组将自动启动，插件将逐个运行。 Defines the
             * array of the identifiers (as entered in config.json) for the
             * plugins, which will automatically start when the editor opens,
             * and the order the plugins will run one-by-one.
             */
            private String[] autostart;

            /**
             * 定义插件配置文件（config.json）的绝对url数组。 Defines the array of absolute URLs
             * to the plugin configuration files (config.json).
             */
            private String[] pluginsData;

            /**
             * 定义存储插件的目录的绝对URL。从4.3版开始就不推荐使用，请使用pluginsData字段中的绝对url Defines the
             * absolute URL to the directory where the plugins are stored.
             * Deprecated since version 4.3, please use the absolute URLs in
             * pluginsData field.
             */
            private String url;

            public String[] getAutostart()
            {
                return autostart;
            }

            public void setAutostart(String[] autostart)
            {
                this.autostart = autostart;
            }

            public String[] getPluginsData()
            {
                return pluginsData;
            }

            public void setPluginsData(String[] pluginsData)
            {
                this.pluginsData = pluginsData;
            }

            public String getUrl()
            {
                return url;
            }

            public void setUrl(String url)
            {
                this.url = url;
            }
        }
    }

    /**
     * 
     * @Description: 文档对象
     * @Author: 况克冬
     * @CreateDate: 2020年3月11日上午11:29:57
     * @Version: 1.0.0
     */
    public class Document
    {
        /**
         * 定义已查看或编辑的源文档的文件类型。 Defines the type of the file for the source viewed
         * or edited document.
         */
        private String fileType;

        /**
         * 定义服务用于文档识别的唯一文档标识符。如果发送已知密钥，则文档将从缓存中获取。每次编辑和保存文档时，都必须重新生成密钥。文档url可以用作密钥，但没有特殊字符，长度限制为20个符号。
         * Defines the unique document identifier used for document recognition
         * by the service. In case the known key is sent the document will be
         * taken from the cache. Every time the document is edited and saved,
         * the key must be generated anew. The document url can be used as the
         * key but without the special characters and the length is limited to
         * 20 symbols.
         */
        private String key;

        /**
         * 为已查看或编辑的文档定义所需的文件名，该文件名在下载文档时也将用作文件名。 Defines the desired file name
         * for the viewed or edited document which will also be used as file
         * name when the document is downloaded.
         */
        private String title;

        /**
         * 定义存储源查看或编辑的文档的绝对URL。 Defines the absolute URL where the source viewed
         * or edited document is stored.
         */
        private String url;

        /**
         * 权限
         */
        private Permissions permissions;

        /**
         * 信息
         */
        private Info info;

        public String getFileType()
        {
            return fileType;
        }

        public void setFileType(String fileType)
        {
            this.fileType = fileType;
        }

        public String getKey()
        {
            return key;
        }

        public void setKey(String key)
        {
            this.key = key;
        }

        public String getTitle()
        {
            return title;
        }

        public void setTitle(String title)
        {
            this.title = title;
        }

        public String getUrl()
        {
            return url;
        }

        public void setUrl(String url)
        {
            this.url = url;
        }

        public Info getInfo()
        {
            return info == null ? new Info() : info;
        }

        public void setInfo(Info info)
        {
            this.info = info;
        }

        public Permissions getPermissions()
        {
            return permissions == null ? new Permissions() : permissions;
        }

        public void setPermissions(Permissions permissions)
        {
            this.permissions = permissions;
        }

        /**
         * 
         * @Description: 信息
         * @Author: 况克冬
         * @CreateDate: 2020年4月29日上午11:22:07
         * @Version: 1.0.0
         */
        public class Info
        {
            /**
             * 定义文档作者/创建者的名称。自5.4版起已弃用，请改用owner。
             */
            private String folder;

            /**
             * 定义文档所有者/创建者的名称。
             */
            private String owner;

            /**
             * 共享设置 显示有关允许与其他用户共享文档的设置的信息： 权限 -具有上述名称的用户的访问权限。可以是“ 完全访问”，“
             * 只读”或“拒绝访问” 类型：字符串 示例：“完全访问” 用户 -该文件将与被共享的用户的名称 类型：字符串
             * 例子：“约翰·史密斯”。
             */
            private String[] sharingSettings;

            /**
             * 定义文档上载日期。
             */
            private String uploaded;

            public String getFolder()
            {
                return folder;
            }

            public void setFolder(String folder)
            {
                this.folder = folder;
            }

            public String getOwner()
            {
                return owner;
            }

            public void setOwner(String owner)
            {
                this.owner = owner;
            }

            public String[] getSharingSettings()
            {
                return sharingSettings;
            }

            public void setSharingSettings(String[] sharingSettings)
            {
                this.sharingSettings = sharingSettings;
            }

            public String getUploaded()
            {
                return uploaded;
            }

            public void setUploaded(String uploaded)
            {
                this.uploaded = uploaded;
            }
        }

        /**
         * 
         * @Description: 权限
         * @Author: 况克冬
         * @CreateDate: 2020年4月29日上午10:46:54
         * @Version: 1.0.0
         */
        public class Permissions
        {
            /**
             * 使用onRequestRestore事件时， 允许显示“
             * 还原”按钮。默认值为false。自5.5版起已弃用，请改为添加onRequestRestore字段。
             */
            private boolean changeHistory;

            /**
             * 定义是否可以注释文档。如果注释权限设置为“ true”，则文档侧栏将包含“
             * 注释”菜单选项；仅当mode参数设置为edit时，文档注释才可用于文档编辑器。默认值与edit参数的值一致。
             */
            private boolean comment;

            /**
             * 定义文档是可以下载还是只能在线查看或编辑。如果下载权限设置为“假”的下载为...菜单选项将是缺席的文件菜单。默认值为true。
             */
            private boolean download = true;

            /**
             * 定义文档是可以编辑还是只能查看。如果编辑权限设置为“ true”，则“ 文件”菜单将包含“
             * 编辑文档”菜单选项；请注意，如果将编辑权限设置为“
             * false”，则将在查看器中打开该文档，即使将mode参数设置为edit，也将无法将其切换到编辑器。默认值为true。
             */
            private boolean edit = true;

            /**
             * 定义是否可以填写表格。如果将mode参数设置为edit，则填写表单仅可用于文档编辑器。默认值与edit或review参数的值一致。
             */
            private boolean fillForms = true;

            /**
             * 定义是否可以更改内容控件设置。仅当mode参数设置为edit时，内容控件修改才可用于文档编辑器。默认值为true
             */
            private boolean modifyContentControl = true;

            /**
             * 定义该过滤器是否可以全局应用（true）影响所有其他用户，或者可以局部应用（false），即仅适用于当前用户。如果将mode参数设置为edit，则过滤器修改仅对电子表格编辑器可用。默认值为true。
             */
            private boolean modifyFilter = true;

            /**
             * 定义是否可以打印文档。如果打印权限设置为“假”的打印菜单选项将是缺席的文件菜单。默认值为true。
             */
            private boolean print = true;

            /**
             * 定义是否可以查看文档。如果审阅权限设置为“ true”，则文档状态栏将包含“
             * 审阅”菜单选项；仅当mode参数设置为edit时，文档审阅仅可用于文档编辑器。默认值与edit参数的值一致。
             */
            private boolean review;

            public boolean isChangeHistory()
            {
                return changeHistory;
            }

            public void setChangeHistory(boolean changeHistory)
            {
                this.changeHistory = changeHistory;
            }

            public boolean isComment()
            {
                return comment;
            }

            public void setComment(boolean comment)
            {
                this.comment = comment;
            }

            public boolean isDownload()
            {
                return download;
            }

            public void setDownload(boolean download)
            {
                this.download = download;
            }

            public boolean isEdit()
            {
                return edit;
            }

            public void setEdit(boolean edit)
            {
                this.edit = edit;
            }

            public boolean isFillForms()
            {
                return fillForms;
            }

            public void setFillForms(boolean fillForms)
            {
                this.fillForms = fillForms;
            }

            public boolean isModifyContentControl()
            {
                return modifyContentControl;
            }

            public void setModifyContentControl(boolean modifyContentControl)
            {
                this.modifyContentControl = modifyContentControl;
            }

            public boolean isModifyFilter()
            {
                return modifyFilter;
            }

            public void setModifyFilter(boolean modifyFilter)
            {
                this.modifyFilter = modifyFilter;
            }

            public boolean isPrint()
            {
                return print;
            }

            public void setPrint(boolean print)
            {
                this.print = print;
            }

            public boolean isReview()
            {
                return review;
            }

            public void setReview(boolean review)
            {
                this.review = review;
            }
        }
    }
}
