<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script charset="UTF-8" src="kindeditor/kindeditor-all-min.js"></script>
    <script charset="UTF-8" src="kindeditor/lang/zh-CN.js"></script>
    <script>
        KindEditor.ready(function (K) {
            window.editor = K.create('#editor_id');
        });
    </script>
</head>
<body>
<textarea id="editor_id" name="content" style="width:700px;height:300px;">
        &lt;strong&gt;HTML内容&lt;/strong&gt;
    </textarea>
</body>
</html>