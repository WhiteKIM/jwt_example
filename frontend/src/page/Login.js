import React, { useState } from 'react';
import { Instance } from './../util/Instance/Instance';
import axios from 'axios';

export default function Login() {
  const [id, setId] = useState('');
  const [password, setPassword] = useState('');

  const LoginButtonClick = () => {
    let data = {
      username: id,
      password: password,
    };

    axios
      .post('/backend/login', JSON.stringify(data), {
        headers: {
          'Content-Type': 'application/json',
        },
      })
      .then((response) => {
        const token = response.headers.get('Authorization');
        localStorage.setItem('jwtToken', token);
        window.location.href = '/';
      });
  };

  return (
    <>
      <div>
        <lable>아이디</lable>
        <input type="text" placeholder="아이디" onChange={(e) => setId(e.target.value)}></input>
        <label>비밀번호</label>
        <input type="password" placeholder="비밀번호" onChange={(e) => setPassword(e.target.value)}></input>
        <button onClick={LoginButtonClick}>로그인</button>
      </div>
    </>
  );
}
