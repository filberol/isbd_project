<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 17.01.2024
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Simple Graph with D3.js</title>
    <script src="https://d3js.org/d3.v6.min.js"></script>
</head>
<body>
<svg width="500" height="500"></svg>
<script>
    // Sample graph data
    const nodes = [
        { id: 1, label: 'Node 1' },
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
