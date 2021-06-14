import React, { useEffect, useState } from 'react';
import { Dropdown } from 'semantic-ui-react';
import PositionService from '../../services/positionService';
import TextEditorService from '../../utils/textEditorService';

export default function PositionFilter() {
  const [positions, setPositions] = useState([]);

  const positionOptions = positions.map((position) => ({
    key: position.positionId,
    value: position.positionId,
    text: TextEditorService.capitalize(position.positionName),
  }));

  useEffect(() => {
    let mounted = true;
    let positionService = new PositionService();
    positionService.getAll().then((result) => {
      if (mounted) {
        setPositions(result.data.data);
      }
    });
    return () => (mounted = false);
  }, []);

  function getPositionId(event, data) {
    // Select position id, later we will send it to redux for re render
    console.log(data.value);
  }

  return (
    <>
      <Dropdown
        placeholder='Select Position'
        fluid
        search
        selection
        onChange={getPositionId}
        options={positionOptions}
      />
    </>
  );
}
