<%@ page contentType="text/html;charset=UTF-8" %>

<script>
    const gutter = document.querySelector(".resizablePanel");
    function resizer(e) {
        window.addEventListener('mousemove', mousemove);
        window.addEventListener('mouseup', mouseup);
        let prevX = e.x;
        const leftPanel = gutter.getBoundingClientRect();

        function mousemove(e) {
            let newX = prevX - e.x;
            gutter.style.width = leftPanel.width - newX + "px";
        }

        function mouseup() {
            window.removeEventListener('mousemove', mousemove);
            window.removeEventListener('mouseup', mouseup);
        }
    }
    gutter.addEventListener('mousedown', resizer);
</script>
