<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html lang="ru-RU">
<head>
    <title>Атлант расправил плечи: Курсовая работа - Чернова Анна, Миху Вадим - P33301</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="https://d3js.org/d3.v6.min.js"></script>
    <jsp:include page="jsp_style.jsp"/>
</head>
<body>
<div id="container">
    <div id="left-panel">
        <h2>${station.name()}</h2>
        <div>Owner: ${company.name()}</div>
        <hr/>
        <c:if test="${warehouse != null}">
            <h3>Warehouse</h3>
            <div>Resources available: ${warehouse.resourceAvailableKm()}</div>
            <div class="table-container">
                <jsp:include page="resource_allocation.jsp">
                    <jsp:param name="resources" value="${resources}"/>
                </jsp:include>
            </div>
        </c:if>
        <hr/>
        <c:if test="${repairBase != null}">
            <h3>Repair base</h3>
            <div>Team capacity: ${repairBase.sizeTeams()}</div>
            <div>Teams hosted: ${repairBase.currTeamsHosted()}</div>
        </c:if>
    </div>
    <div id="center">
        <div id="search-bar1">
            <label for="station_search">Station name</label>
            <input type="text" id="station_search" placeholder="Type station name">
        </div>
        <div id="search-bar2">
            <label for="brigade_search">Brigade name</label>
            <input type="text" id="brigade_search" placeholder="Type brigade name">
        </div>
        <div id="canvas">
            <svg width="550" height="550"></svg>
        </div>
    </div>
    <div id="right-panel"></div>
</div>
<script>
    // Sample graph data
    const nodes = [
        <c:if test="${station != null}">
        {id: 1, label: '${station.name()}'},
        </c:if>
        <c:if test="${warehouse != null}">
        {id: 2, label: 'Warehouse'},
        </c:if>
        <c:if test="${repairBase != null}">
        {id: 3, label: 'Repair base'},
        </c:if>
        <c:forEach items="${related}" var="r_station" varStatus="loop_stat">
        {id: ${loop_stat.index + 4}, label: '${r_station.name()}'},
        </c:forEach>
    ];

    const links = [
        <c:if test="${warehouse != null}">
        {source: 2, target: 1},
        </c:if>
        <c:if test="${repairBase != null}">
        {source: 3, target: 1},
        </c:if>
        <c:forEach items="${related}" var="r_station" varStatus="loop_stat">
        {source: ${loop_stat.index + 4}, target: 1},
        </c:forEach>
    ];

    drag = simulation => {

        function dragstarted(event, d) {
            if (!event.active) simulation.alphaTarget(0.3).restart();
            d.fx = d.x;
            d.fy = d.y;
        }

        function dragged(event, d) {
            d.fx = event.x;
            d.fy = event.y;
        }

        function dragended(event, d) {
            if (!event.active) simulation.alphaTarget(0);
            d.fx = null;
            d.fy = null;
        }

        return d3.drag()
            .on("start", dragstarted)
            .on("drag", dragged)
            .on("end", dragended);
    }

    // Create an SVG container
    const svg = d3.select('svg');

    // Create a force simulation
    const simulation = d3.forceSimulation(nodes)
        .force('link', d3.forceLink(links).id(d => d.id))
        .force('charge', d3.forceManyBody().strength(-5000))
        .force('center', d3.forceCenter(250, 250));

    // Draw links
    const link = svg.selectAll('line')
        .data(links)
        .enter().append('line')
        .attr('stroke', 'black');

    // Draw nodes
    const node = svg.selectAll('circle')
        .data(nodes)
        .enter().append('circle')
        .attr('r', 20)
        .attr('fill', d => colorScale(d))
        .call(drag(simulation));

    // Add labels to nodes
    const label = svg.selectAll('text')
        .data(nodes)
        .enter().append('text')
        .text(d => d.label)
        .attr('text-anchor', 'right')
        .attr('dy', 4)
        .call(drag(simulation));


    // Update the simulation on each tick
    simulation.on('tick', () => {
        link
            .attr('x1', d => d.source.x)
            .attr('y1', d => d.source.y)
            .attr('x2', d => d.target.x)
            .attr('y2', d => d.target.y);

        node
            .attr('cx', d => d.x)
            .attr('cy', d => d.y);

        label
            .attr('x', d => d.x)
            .attr('y', d => d.y);
    });

    function colorScale(label) {
        if (label.label === '${station.name()}') return 'green'
        if (label.label === 'Warehouse') return 'cyan'
        if (label.label === 'Repair base') return 'lightblue'
        return 'grey'
    }


</script>

</body>
</html>
