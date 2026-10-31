
import unittest
import tempfile
import os
from unittest.mock import patch

from app import app


class TestApp(unittest.TestCase):

    def setUp(self):
        app.config["TESTING"] = True
        self.client = app.test_client()

        self.temp_dir = tempfile.TemporaryDirectory()
        self.ficheiro = os.path.join(
            self.temp_dir.name,
            "participantes.json"
        )

        self.patch_ficheiro = patch("app.FICHEIRO", self.ficheiro)
        self.patch_ficheiro.start()

    def tearDown(self):
        self.patch_ficheiro.stop()
        self.temp_dir.cleanup()

    def test_home_status_code(self):
        resposta = self.client.get("/")
        self.assertEqual(resposta.status_code, 200)

    def test_registo_com_sucesso(self):
        dados = {
            "nome": "Alexandre Silva",
            "email": "alexandre@email.com",
            "codigo_postal": "4780-000"
        }

        with patch("app.consultar_localidade", return_value="Santo Tirso"):
            resposta = self.client.post("/registar", json=dados)

        self.assertEqual(resposta.status_code, 201)
        self.assertEqual(
            resposta.json["participante"]["localidade"],
            "Santo Tirso"
        )

    def test_email_invalido(self):
        dados = {
            "nome": "Alexandre",
            "email": "email_invalido",
            "codigo_postal": "4780-000"
        }

        resposta = self.client.post("/registar", json=dados)

        self.assertEqual(resposta.status_code, 400)

    def test_codigo_postal_invalido(self):
        dados = {
            "nome": "Alexandre",
            "email": "alexandre@email.com",
            "codigo_postal": "123"
        }

        resposta = self.client.post("/registar", json=dados)

        self.assertEqual(resposta.status_code, 400)


if __name__ == "__main__":
    unittest.main()
