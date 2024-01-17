<%@ page contentType="text/html;charset=UTF-8"%>

<html lang="ru-RU">
<head>
    <title>Атлант расправил плечи: Курсовая работа - Чернова Анна, Миху Вадим - P33301</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="https://d3js.org/d3.v6.min.js"></script>
    <style>
        body {
            margin: 0;
            padding: 0;
        }

        #container {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        #left-panel,
        #right-panel {
            width: 25vw;
            height: 100%;
            background-color: lightgray;
        }

        #center {
            flex: 1;
            height: 100%;
            display: flex;
            flex-direction: column;
        }

        #search-bar1,
        #search-bar2 {
            display: flex;
            align-items: center;
            width: 100%;
            height: 50px;
            background-color: lightblue;
            margin-bottom: 10px;
        }

        #canvas {
            display: flex;
            flex-direction: column;
            align-items: center;
            flex: 1;
            background-color: white;
        }
    </style>
</head>
<body>
<div id="container">
    <div id="left-panel"></div>
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
            Displaying station with name ${station.name()}
            <svg width="500" height="500"></svg>
        </div>
    </div>
    <div id="right-panel"></div>
</div>
<script>
    // Sample graph data
    const nodes = [
        { id: 1, label: '${station.name()}'},
        { id: 2, label: 'Node 2' },
        { id: 3, label: 'Node 3' },
    ];

    const links = [
        { source: 1, target: 2 },
        { source: 2, target: 3 },
    ];

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
        .attr('fill', 'blue')

    // Add labels to nodes
    const label = svg.selectAll('text')
        .data(nodes)
        .enter().append('text')
        .text(d => d.label)
        .attr('text-anchor', 'right')
        .attr('dy', 4);

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
</script>

</body>
</html>
