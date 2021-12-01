import React from 'react';
import { Radio } from 'antd';
import './FloorPlan.css';
import { MapContainer, TileLayer } from 'react-leaflet';

import * as L from 'leaflet';
// import * as GeoJSON from 'geojson';

const styles = {
  building: {
    color: "#BF4320",
    opacity: 0.5,
    fillOpacity: 1,
    fillColor: "#fafafa",
    dashArray: "5 10"
  },
  level: {
    fillColor: "#777",
    fillOpacity: 1,
    color: "black",
    weight: 1
  },
  spaces: (feature) => {
    return {
      color: "black",
      weight: 1,
      opacity: 1,
      fillOpacity: 1,
      fillColor: "white",
    }
  },
  obstruction: {
    weight: 0,
    fillColor: "black",
    fillOpacity: 1
  },
  node: {
    radius: 2,
    fillColor: "#BF4320",
    color: "#000",
    weight: 1,
    opacity: 1,
    fillOpacity: 0.4
  }
}

async function loadData(filename) {
    return (await fetch(`${filename}`)).json();
}

let levelId, building, level, space;

async function fetchData() {
    levelId = "5bd856a84bd0ef0e32000001";
    building = await loadData('../public/Demo Office MVF/building.geojson');
    level = await loadData(`../public/Demo Office MVF/level/${levelId}.geojson`);
    space = await loadData(`../public/Demo Office MVF/space/${levelId}.geojson`);
}

function FloorPlan() {
    // fetchData();

    // const [map, setMap] = useState();

    // const buildingLayer = L.geoJSON(building, {
    //     style: styles.building
    // })

    // const levelLayer = L.geoJSON(level, { style: styles.level })

    // const spaceLayer = L.geoJSON(space, 
    //     { style: styles.spaces,
    //         filter: (feature) => {
    //         return feature.geometry.type === "Polygon"
    //         }
    //     }
    // )



    // map = L.map('map', {
    //     maxBounds: buildingLayer.getBounds(),
    // });
    
    // map.fitBounds(buildingLayer.getBounds());

    function SelectFloor() {
        const [value, setValue] = React.useState(1);
        const onChange = e => {
            console.log('radio checked', e.target.value);
            setValue(e.target.value);
          };
        return (
            <div class="radios">
                <Radio.Group onChange={onChange} value={value} size="large">
                    <Radio value={1} className="radio"> Floor 1</Radio>
                    <Radio value={2} className="radio"> Floor 2</Radio>
                    <Radio value={3} className="radio"> Floor 3</Radio>
                </Radio.Group>
            </div>
        )
    }

    return (
        <div>
            <SelectFloor />
            <div id="map"></div>
        </div>
    )
}

export default FloorPlan
