export default {
    downloadFile (data, fileName) {
        alert('downloadFile');
        // const blob = new Blob([data], { type: 'application/octet-binary' });
        // const blob = new Blob([data], { type: 'application/vnd.ms-excel' });
        const blob = new Blob([data]);
        console.log('blob:', blob);
        if (window.navigator && window.navigator.msSaveOrOpenBlob) {
            // for IE
            window.navigator.msSaveOrOpenBlob(blob, fileName);
        } else {
            // for Non-IE (chrome, firefox etc.)
            const reader = new FileReader();
            reader.readAsDataURL(blob);
            reader.onload = e => {
                const a = document.createElement('a'); // 创建的标签
                a.download = fileName; // 下载后文件名
                a.href = e.target.result; // 创建url
                document.body.appendChild(a); // 把 创建的标签追加到body里
                a.click(); // 点击标签下载
                a.remove(); // 移除标签
            };
        }
    }
};
